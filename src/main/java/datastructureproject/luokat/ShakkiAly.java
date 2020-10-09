package datastructureproject.luokat;

import chess.bot.ChessBot;
import chess.engine.GameState;
import chess.model.Side;
import datastructureproject.luokat.apulaiset.Matikka;
import datastructureproject.luokat.apulaiset.ShakkiApu;
import datastructureproject.luokat.tietorakenteet.*;
import datastructureproject.luokat.nappulat.Kuningas;
import datastructureproject.luokat.nappulat.Nappula;

public class ShakkiAly implements ChessBot {
    public static final String LUOVUTUS = "luovutus";
    private int syvyysMax;
    private boolean alphaBeta;

    private Pelilauta lauta;
    private Siirto viimeSiirto;
    
    private long haarat = 0;

    public ShakkiAly(boolean alphaBeta) {
        alusta(alphaBeta, 4);
    }

    public ShakkiAly(boolean alphaBeta, int syvyysMax) {
        alusta(alphaBeta, syvyysMax);
    }

    private void alusta(boolean alphaBeta, int syvyysMax) {
        this.lauta = new Pelilauta();
        this.alphaBeta = alphaBeta;
        this.syvyysMax = syvyysMax;
        viimeSiirto = null;
    }

    /**
     * Kertoo käyttääkö tämä instanssi AlphaBeta-karsintaa vai ei. 
     * @return onko käytössä AlphaBeta vai ei
     */
    public boolean onAlphaBeta() {
        return alphaBeta;
    }

    /**
     * Kertoo MiniMaxille asetetun syvyyden
     * @return MiniMaxille asetettu syvyys
     */
    public int getSyvyysMax() {
        return syvyysMax;
    }

    /**
     * Viime siirron aikana tarkasteltujen haarojen(siirtojen) määrä.
     * Hyödyllinen tehokkuustestauksessa
     * @return tarkasteltujen siirtojen määrä
     */
    public long getHaarat() {
        return haarat;
    }

    /**
     * Keksii aiemman pelitilanteen pohjalta uuden liikkeen.
     * 
     * @return Nappulan siirtämisen tiedot UCI-formaatissa
     */
    @Override
    public String nextMove(GameState gamestate) {
        //Luetaan koko pelitilanne laudalle, esim. keskeytyneen pelin vuoksi
        this.lauta = new Pelilauta();
        for (String siirto: gamestate.moves) {
            lauta = lauta.toteutaSiirto(new Siirto(siirto));
        }

        haarat = 0;
        // Pistetään MiniMax algoritmi pyörimään
        ArvoSiirto arvoSiirto = maxArvo(lauta, gamestate.playing, Integer.MIN_VALUE, Integer.MAX_VALUE, syvyysMax);
        if (arvoSiirto.getSiirto() == null) {
            return LUOVUTUS;
        }
        
        //Toteutetaan siirto omalla laudalla
        lauta = lauta.toteutaSiirto(arvoSiirto.getSiirto());
        //Palautetaan siirto rajapinnan mukaan
        return arvoSiirto.getSiirto().getUCIString();
    }

    /**
     * Filtteröi pois peliliikkeet jotka säilyttävät / aiheuttavat pelaajan oman kuninkaan shakkiin joutumisen.
     * @param lauta tämän hetkinen pelitilanne
     * @param kaikkiSiirrot kaikki siirrot jota pelaaja jonka vuoro on, pystyy tekemään 
     * @param puoli pelaaja jonka vuoro on
     * @return listan siirroista josta on filtteröity pois shakkitilanteen säilyttävät / aiheuttavat siirrot
     */
    public SiirtoLista vainSallitut(Pelilauta lauta, SiirtoLista kaikkiSiirrot, Side puoli) {
        SiirtoLista siirrot = new SiirtoLista();

        for (int i = 0; i < kaikkiSiirrot.size(); i++) {
            Siirto siirto = kaikkiSiirrot.get(i);
            Pelilauta uusiLauta = lauta.toteutaSiirto(siirto);
            Kuningas k = uusiLauta.etsiKuningas(puoli);
            if (k != null && !(k.olenUhattuna(uusiLauta))) {
                siirrot.add(siirto);
            }
        }

        return siirrot;
    }

    /**
     * Simuloidaan omaa vuoroa minmaxissa
     * 
     * @param lauta tamanhetkinen pelitilanne
     * @param puoli max-pelaajan puoli
     * @param alpha alphabeta karsinnan alpha-arvo
     * @param beta alphabeta karsinnan beta-arvo
     * @param syvyys syvyys jota tarkastellaan pelipuussa
     * @return max-pelaajan valitseman haaran 'arvo'
     */
    public ArvoSiirto maxArvo(Pelilauta lauta, Side puoli, int alpha, int beta, int syvyys) {
        SiirtoLista siirrot;
        if (syvyys <= 0 || (siirrot = vainSallitut(lauta, lauta.generoiSiirrot(puoli), puoli)).isEmpty()) {
            return new ArvoSiirto(laudanArvo(lauta, puoli), null);
        }
        ArvoSiirto maxinSiirto = new ArvoSiirto(Integer.MIN_VALUE, null);
        for (int i = 0; i < siirrot.size(); i++) {
            haarat++;
            Siirto siirto = siirrot.get(i);
            Pelilauta uusiLauta = lauta.toteutaSiirto(siirto);
            ArvoSiirto minninSiirto = minArvo(uusiLauta, ShakkiApu.vastustaja(puoli), alpha, beta, syvyys - 1);

            //Päivitetään maxin parasta siirtoa, jos tämä haara on johtanut minnille huonomman arvon kuin aiemmassa
            if (maxinSiirto.getArvo() < minninSiirto.getArvo()) {
                maxinSiirto.set(minninSiirto.getArvo(), siirto);
            }

            alpha = Matikka.maksimi(alpha, minninSiirto.getArvo());
            if (alphaBeta && alpha >= beta) {
                return maxinSiirto;
            }
        }

        return maxinSiirto;
    }

    /**
     * Simuloidaan vastustajan vuoroa minmaxissa
     * 
     * @param lauta tamanhetkinen pelitilanne
     * @param puoli min-pelaajan puoli
     * @param alpha alphabeta karsinnan alpha-arvo
     * @param beta alphabeta karsinnan beta-arvo
     * @param syvyys syvyys jota tarkastellaan pelipuussa
     * @return min-pelaajan valitseman haaran 'arvo'
     */
    public ArvoSiirto minArvo(Pelilauta lauta, Side puoli, int alpha, int beta, int syvyys) {
        Side vastustajanPuoli = ShakkiApu.vastustaja(puoli);
        SiirtoLista siirrot;
        if (syvyys <= 0 || (siirrot = vainSallitut(lauta, lauta.generoiSiirrot(puoli), puoli)).isEmpty()) {
            return new ArvoSiirto(laudanArvo(lauta, puoli), null);
        }

        ArvoSiirto minninSiirto = new ArvoSiirto(Integer.MAX_VALUE, null);
        for (int i = 0; i < siirrot.size(); i++) {
            haarat++;
            Siirto siirto = siirrot.get(i);
            Pelilauta uusiLauta = lauta.toteutaSiirto(siirto);
            ArvoSiirto maxinSiirto = maxArvo(uusiLauta, vastustajanPuoli, alpha, beta, syvyys - 1);

            //Päivitetään minnin parasta siirtoa, jos tämä haara on johtanut maxille huonomman arvon kuin aiemmassa
            if (minninSiirto.getArvo() > maxinSiirto.getArvo()) {
                minninSiirto.set(maxinSiirto.getArvo(), siirto);
            }

            beta = Matikka.minimi(beta, maxinSiirto.getArvo());
            if (alphaBeta && alpha >= beta) {
                return minninSiirto;
            }
        }

        return minninSiirto;
    }

    /**
     * Heuristiikka jolla arvioidaan pelitilanteen 'arvoa' jommankumman puolen pelaajan kannalta
     * 
     * @param lauta pelilaudan tilanne, jota tarkastellaan
     * @param puoli kumman puolen pelaajan kannalta tilannetta tarkastellaan
     * @return laudan arvo kokonaislukuna. Jos arvo isompi kuin 0, niin tilanne on hyvä
     */
    public static int laudanArvo(Pelilauta lauta, Side puoli) {
        int i = 0;
        for (int y = 0; y < lauta.getKoko(); y++) {
            for (int x = 0; x < lauta.getKoko(); x++) {
                Nappula nappula = lauta.lauta[y][x];
                if (nappula != null) {
                    i += (nappula.getPuoli() == puoli ? +1 : -1) * nappula.getArvo();
                }
            }
        }
        return i;
    }
    
}

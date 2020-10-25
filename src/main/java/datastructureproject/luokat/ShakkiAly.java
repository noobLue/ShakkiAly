package datastructureproject.luokat;

import chess.bot.ChessBot;
import chess.engine.GameState;
import chess.model.Side;
import datastructureproject.luokat.apulaiset.Matikka;
import datastructureproject.luokat.apulaiset.ShakkiApu;
import datastructureproject.luokat.evaluointi.Evaluaattori;
import datastructureproject.luokat.evaluointi.OmaEvaluaattori;
import datastructureproject.luokat.tietorakenteet.*;
import datastructureproject.luokat.nappulat.Kuningas;

public class ShakkiAly implements ChessBot {
    public static final String LUOVUTUS = null;
    private Evaluaattori evaluaattori;

    private int syvyysMax;
    private boolean alphaBeta;

    private Pelilauta pelilauta;
    
    private long haarat = 0;

    public ShakkiAly(boolean alphaBeta) {
        alusta(alphaBeta, 4); 
    }

    public ShakkiAly(boolean alphaBeta, int syvyysMax) {
        alusta(alphaBeta, syvyysMax);
    }

    private void alusta(boolean alphaBeta, int syvyysMax) {
        evaluaattori = new OmaEvaluaattori();
        this.pelilauta = new Pelilauta();
        this.alphaBeta = alphaBeta;
        this.syvyysMax = syvyysMax;
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
        this.pelilauta = new Pelilauta();
        for (String siirto: gamestate.moves) {
            pelilauta.siirto(new Siirto(siirto));
        }

        haarat = 0;
        // Pistetään MiniMax algoritmi pyörimään
        Tupla<Double, Siirto> arvoSiirto = negaMax(pelilauta, gamestate.playing);
        if (arvoSiirto.getToka() == null) {
            return LUOVUTUS;
        }

        //Palautetaan siirto rajapinnan mukaan
        return arvoSiirto.getToka().getUCIString();
    }

    /**
     * Filtteröi pois peliliikkeet jotka säilyttävät / aiheuttavat pelaajan oman kuninkaan shakkiin joutumisen.
     * @param lauta tämän hetkinen pelitilanne
     * @param kaikkiSiirrot kaikki siirrot jota pelaaja jonka vuoro on, pystyy tekemään 
     * @param puoli pelaaja jonka vuoro on
     * @return listan siirroista josta on filtteröity pois shakkitilanteen säilyttävät / aiheuttavat siirrot
     */
    public Lista<Siirto> vainSallitut(Pelilauta lauta, Lista<Siirto> kaikkiSiirrot, Side puoli) {
        Lista<Siirto> siirrot = new Lista<>();

        for (int i = 0; i < kaikkiSiirrot.size(); i++) {
            Siirto siirto = kaikkiSiirrot.get(i);


            lauta.siirto(siirto);
            Kuningas k = lauta.etsiKuningas(puoli);
            if (k != null && !(k.olenUhattuna(lauta))) {
                siirrot.add(siirto);
            }
            lauta.peruutaSiirto();
        }

        return siirrot;
    }

    public static final double MAX = 1_000_000d;
    public static final double MIN = -MAX;
    public static final double DELTA = 0.0001d;

    /**
     * Aloitetaan rekursiivinen negaMax algoritmi
     * 
     * @param lauta tamanhetkinen pelitilanne
     * @param vuoro kumman pelaajan siirto halutaan etsiä
     * @return 
     */
    public Tupla<Double, Siirto> negaMax(Pelilauta lauta, Side vuoro) {
        return negaMax(lauta, vuoro, MIN, MAX, syvyysMax);
    } 

    /**
     * MiniMaxin yksi rekursio
     * 
     * @param lauta tamanhetkinen pelitilanne
     * @param vuoro kumman vuoro
     * @param alpha alphabeta karsinnan alpha-arvo
     * @param beta alphabeta karsinnan beta-arvo
     * @param syvyys syvyys jota tarkastellaan pelipuussa
     * @return 
     */
    private Tupla<Double, Siirto> negaMax(Pelilauta lauta, Side vuoro, double alpha, double beta, int syvyys) {
        Lista<Siirto> siirrot = lauta.generoiSiirrot(vuoro);
        siirrot = vainSallitut(lauta, siirrot, vuoro);
        if (siirrot.size() == 0) {
            return new Tupla<>(lauta.onShakissa(vuoro) ? MIN - syvyys : 0, null);
        }

        if (syvyys <= 0) {
            double laudanArvo = evaluaattori.getLaudanArvo(lauta, vuoro);
            return new Tupla<>(laudanArvo, null);
        }
        
        Tupla<Double, Siirto> parasSiirto = new Tupla<>(MIN, null);
        for (int i = 0; i < siirrot.size(); i++) {
            haarat++;
            Siirto siirto = siirrot.get(i);
            lauta.siirto(siirto);
            Tupla<Double, Siirto> vastustajanSiirto = negaMax(lauta, ShakkiApu.vastustaja(vuoro), -beta, -alpha, syvyys - 1);

            //Päivitetään tämän pelaajan parasta siirtoa, 
            //jos tämä haara on johtanut toiselle pelaajalle huonomman arvon kuin aiemmassa
            if (-vastustajanSiirto.getEka() > parasSiirto.getEka()) {
                parasSiirto.set(-vastustajanSiirto.getEka(), siirto);
            }
            lauta.peruutaSiirto();

            alpha = Matikka.maksimi(alpha, parasSiirto.getEka());
            if (alphaBeta && alpha >= beta) {
                break;
            }
        }

        return parasSiirto;
    }


}

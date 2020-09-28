package datastructureproject;

import chess.bot.ChessBot;
import chess.engine.GameState;
import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.ShakkiTemplaatti;
import datastructureproject.luokat.Siirto;
import datastructureproject.luokat.SiirtoLista;
import datastructureproject.luokat.nappulat.Kuningas;
import datastructureproject.luokat.nappulat.Nappula;

public class ShakkiAly implements ChessBot {
    private static final int SYVYYS = 3;

    private Pelilauta lauta;

    public ShakkiAly() {
        lauta = new Pelilauta(new ShakkiTemplaatti());
    }

    @Override
    public String nextMove(GameState gamestate) {
        if (gamestate.getMoveCount() > 0) {
            Siirto siirto = new Siirto(gamestate.getLatestMove());
            lauta = lauta.toteutaSiirto(siirto);
        }
        //Haetaan kaikki meidan mahdolliset liikkeet 
        //ja karsitaan niistä pois tilanteet jotka johtavat oman kuninkaan shakitukseen
        SiirtoLista siirrot = vainValiditSiirrot(lauta, lauta.kaikkiLiikeet(gamestate.playing), gamestate.playing);
        if (siirrot.isEmpty()) {
            return "a0a0"; //luovutus
        }

        //Alustetaan parasSiirto johonkin satunnaiseen siirtoon
        Siirto parasSiirto = siirrot.get((int) (System.currentTimeMillis() % siirrot.size()));
        int parasArvo = Integer.MIN_VALUE;
        //Käydään läpi mahdolliset siirrot ja aloitetaan siirroille minmax vastustajan nakokulmasta
        for (int i = 0; i < siirrot.size(); i++) {
            Siirto seuraava = siirrot.get(i);
            Pelilauta uusiLauta = lauta.toteutaSiirto(seuraava);
            Side vastustaja = gamestate.playing == Side.WHITE ? Side.BLACK : Side.WHITE;
            int maxArvo = minValue(uusiLauta, vastustaja, Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
            if (maxArvo > parasArvo) {
                parasSiirto = seuraava;
                parasArvo = maxArvo;
            }
        }

        //Toteutetaan siirto omalla laudalla
        lauta = lauta.toteutaSiirto(parasSiirto);
        //Palautetaan siirto rajapinnan mukaan
        return parasSiirto.getUCIString();
    }

    /**
     * Filtteröi pois peliliikkeet jotka säilyttävät / aiheuttavat pelaajan oman kuninkaan shakkiin joutumisen.
     * @param lauta tämän hetkinen pelitilanne
     * @param kaikkiSiirrot kaikki siirrot jota pelaaja jonka vuoro on, pystyy tekemään 
     * @param puoli pelaaja jonka vuoro on
     * @return listan siirroista josta on filtteröity pois shakkitilanteen säilyttävät / aiheuttavat siirrot
     */
    public SiirtoLista vainValiditSiirrot(Pelilauta lauta, SiirtoLista kaikkiSiirrot, Side puoli) {
        SiirtoLista siirrot = new SiirtoLista();

        for (int i = 0; i < kaikkiSiirrot.size(); i++) {
            Siirto s = kaikkiSiirrot.get(i);
            Pelilauta sLauta = lauta.toteutaSiirto(s);
            Kuningas k = sLauta.etsiKuningas(puoli);
            if (!(k.olenUhattuna(sLauta))) {
                siirrot.add(s);
            }
        }

        return siirrot;
    }

    /**
     * Simuloidaan omaa vuoroa minmaxissa
     * 
     * @param peliLauta tamanhetkinen pelitilanne
     * @param puoli max-pelaajan puoli
     * @param alpha alphabeta karsinnan alpha-arvo
     * @param beta alphabeta karsinnan beta-arvo
     * @param syvyys syvyys jota tarkastellaan pelipuussa
     * @return max-pelaajan valitseman haaran 'arvo'
     */
    public int maxValue(Pelilauta peliLauta, Side puoli, int alpha, int beta, int syvyys) {
        SiirtoLista kaikkiSiirrot = vainValiditSiirrot(peliLauta, peliLauta.kaikkiLiikeet(puoli), puoli);
        if (kaikkiSiirrot.isEmpty() || syvyys >= SYVYYS) {
            return laudanArvo(peliLauta, puoli);
        }

        int value = Integer.MIN_VALUE;
        for (int i = 0; i < kaikkiSiirrot.size(); i++) {
            Siirto siirto = kaikkiSiirrot.get(i);
            Pelilauta uusiLauta = peliLauta.toteutaSiirto(siirto);
            int minVal = minValue(uusiLauta, puoli == Side.WHITE ? Side.BLACK : Side.WHITE, alpha, beta, syvyys + 1);

            value = value > minVal ? value : minVal;
            alpha = alpha > minVal ? alpha : minVal;

            if (alpha >= beta) {
                return value;
            }
        }

        return value;
    }

    /**
     * Simuloidaan vastustajan vuoroa minmaxissa
     * 
     * @param peliLauta tamanhetkinen pelitilanne
     * @param puoli min-pelaajan puoli
     * @param alpha alphabeta karsinnan alpha-arvo
     * @param beta alphabeta karsinnan beta-arvo
     * @param syvyys syvyys jota tarkastellaan pelipuussa
     * @return min-pelaajan valitseman haaran 'arvo'
     */
    public int minValue(Pelilauta peliLauta, Side puoli, int alpha, int beta, int syvyys) {
        SiirtoLista kaikkiSiirrot = vainValiditSiirrot(peliLauta, peliLauta.kaikkiLiikeet(puoli), puoli);
        if (kaikkiSiirrot.isEmpty() || syvyys >= SYVYYS) {
            return laudanArvo(peliLauta, puoli == Side.WHITE ? Side.BLACK : Side.WHITE);
        }

        int value = Integer.MAX_VALUE;
        for (int i = 0; i < kaikkiSiirrot.size(); i++) {
            Siirto siirto = kaikkiSiirrot.get(i);
            Pelilauta uusiLauta = peliLauta.toteutaSiirto(siirto);
            int minVal = maxValue(uusiLauta, puoli == Side.WHITE ? Side.BLACK : Side.WHITE, alpha, beta, syvyys + 1);

            value = value < minVal ? value : minVal;
            beta = beta < minVal ? beta : minVal;

            if (alpha >= beta) {
                return value;
            }
        }

        return value;
    }

    /**
     * Heurestiikka jolla arvioidaan pelitilanteen 'arvoa'
     * jommankumman puolen pelaajan kannalta
     * 
     * @param peliLauta pelilaudan tilanne, jota tarkastellaan
     * @param puoli kumman puolen pelaajan kannalta tilannetta tarkastellaan
     * @return laudan arvo kokonaislukuna. Jos arvo isompi kuin 0, niin tilanne on hyvä
     */
    public static int laudanArvo(Pelilauta peliLauta, Side puoli) {
        int i = 0;
        for (int y = 0; y < peliLauta.getKoko(); y++) {
            for (int x = 0; x < peliLauta.getKoko(); x++) {
                Nappula nappula = peliLauta.lauta[y][x];
                if (nappula != null) {
                    i += (nappula.getPuoli() == puoli ? +1 : -1) * nappula.getArvo();
                }
            }
        }
        return i;
    }

    /**
     * Laudan tulostus (debugausta varten)
     */
    public void printtaa() {
        lauta.printtaa();
    }
    
}

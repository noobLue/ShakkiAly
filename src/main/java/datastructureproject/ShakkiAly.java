package datastructureproject;

import java.util.ArrayList;

import chess.bot.ChessBot;
import chess.engine.GameState;
import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.ShakkiTemplaatti;
import datastructureproject.luokat.Siirto;
import datastructureproject.luokat.nappulat.Kuningas;

public class ShakkiAly implements ChessBot {
    private Pelilauta lauta;

    public ShakkiAly() {
        lauta = new Pelilauta(new ShakkiTemplaatti());
    }

    @Override
    public String nextMove(GameState gamestate) {
        Siirto siirto = new Siirto(gamestate.getLatestMove());
        lauta = lauta.toteutaSiirto(siirto);


        Siirto s = filtteroiInvaliditSiirrot(lauta, lauta.kaikkiLiikeet(gamestate.playing), gamestate.playing).get(0);
        lauta = lauta.toteutaSiirto(s);
        return s.getUCIString();
    }

    /**
     * Filtteröi pois peliliikkeet jotka säilyttävät / aiheuttavat pelaajan oman kuninkaan shakkiin joutumisen.
     * @param lauta tämän hetkinen pelitilanne
     * @param kaikkiSiirrot kaikki siirrot jota pelaaja jonka vuoro on, pystyy tekemään 
     * @param puoli pelaaja jonka vuoro on
     * @return listan siirroista josta on filtteröity pois shakkitilanteen säilyttävät / aiheuttavat siirrot
     */
    public ArrayList<Siirto> filtteroiInvaliditSiirrot(Pelilauta lauta, ArrayList<Siirto> kaikkiSiirrot, Side puoli) {
        ArrayList<Siirto> siirrot = new ArrayList();


        for (Siirto s: kaikkiSiirrot) {
            Pelilauta sLauta = lauta.toteutaSiirto(s);
            Kuningas k = sLauta.etsiKuningas(puoli);
            if (!(k.olenUhattuna(sLauta))) {
                siirrot.add(s);
            }
        }

        return siirrot;
    }
    
}

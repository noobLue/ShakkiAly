package datastructureproject.tehokkuus;

import java.util.ArrayList;
import java.util.List;

import chess.bot.ChessBot;
import chess.engine.GameState;
import chess.model.Side;
import datastructureproject.luokat.ShakkiAly;

public class TehokkuusTesti {
    private ChessBot bot;
    private List<GameState> gsList = new ArrayList<>();

    public void setGsList(List<GameState> gsList) {
        this.gsList = gsList;
    }


    public static void main(String[] args) {
        /*
        Set your bot and tests here.
        */

        ShakkiAly[] botit = new ShakkiAly[14];

        for (int i = 0; i < botit.length / 2; i++) {
            botit[i * 2 + 1] = new ShakkiAly(false, i);
            botit[i * 2] = new ShakkiAly(true, i);
        }
        
        for (int i = 0; i < botit.length; i++) {
            aika(botit[i]);
        }

    }

    public static void aika(ShakkiAly aly) {
        GameState state = new GameState();
        state.setMoves("a6a4");
        state.playing = Side.WHITE;

        System.out.println("Tarkastellaan ShakkiAlya, AlphaBeta käytössä: " 
            + aly.onAlphaBeta() + ", maksimisyvyydellä: " + aly.getSyvyysMax());
        long alku = System.nanoTime();
        aly.nextMove(state);
        long kesti = System.nanoTime() - alku;
        double millis = kesti / 1_000_000d;
        System.out.println("    Liikkeen haussa kesti: " 
            + kesti + " nanosekunttia  / " + millis + " millisekunttia.");
        System.out.println("    Käytiin läpi yhteensä " 
            + aly.getHaarat() + " nodea");
        System.out.println("    Yhden noden läpikäymiseen kului aikaa: " 
            + (millis / (double) aly.getHaarat()) + " millisekunttia");
        System.out.println("    Keskimääräinen haarautuvuus: " 
            + (aly.getSyvyysMax() > 0 ? Math.pow(aly.getHaarat(), 1 / (double) aly.getSyvyysMax()) : 0) + " nodea");
    }
}

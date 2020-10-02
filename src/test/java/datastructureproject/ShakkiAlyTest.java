package datastructureproject;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.engine.GameState;
import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.ShakkiTemplaatti;

public class ShakkiAlyTest {
    @Test
    public void sallitunLiikkeenGenerointi(){
        ShakkiAly aly = new ShakkiAly();
        GameState state = new GameState();
        String moves = "a2a4";
        state.setMoves(moves);
        state.playing = Side.BLACK;
        String mustanLiike = aly.nextMove(state);

        moves += ","+mustanLiike;
        state.setMoves(moves);
        state.playing = Side.WHITE;
        String valkoisenLiike = aly.nextMove(state);

        assertNotEquals("a0a0", mustanLiike);
        assertNotEquals("0000", mustanLiike);

        assertNotEquals("a0a0", valkoisenLiike);
        assertNotEquals("0000", valkoisenLiike);
    }

    @Test
    public void heuristiikkaNayttaaPelinLoppumisTilanteen(){
        char[][] alku = new char[][]{
            { '0', '0', '0', 'k', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
        };

        Pelilauta lauta = new Pelilauta(new ShakkiTemplaatti(alku));

        assertTrue(ShakkiAly.laudanArvo(lauta, Side.WHITE) > 0);
        assertTrue(ShakkiAly.laudanArvo(lauta, Side.BLACK) < 0);
    }

}

package datastructureproject;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.engine.GameState;
import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.ShakkiAly;

public class ShakkiAlyTest {
    @Test
    public void sallitunLiikkeenGenerointi(){
        ShakkiAly aly = new ShakkiAly(true, 1);
        GameState state = new GameState();
        String moves = "a2a4";
        state.setMoves(moves);
        state.playing = Side.BLACK;
        String mustanLiike = aly.nextMove(state);

        moves += ","+mustanLiike;
        state.setMoves(moves);
        state.playing = Side.WHITE;
        String valkoisenLiike = aly.nextMove(state);

        assertNotEquals(ShakkiAly.LUOVUTUS, mustanLiike);
        assertNotEquals(ShakkiAly.LUOVUTUS, valkoisenLiike);
    }

    @Test
    public void sallitunLiikkeenGenerointi2(){
        ShakkiAly aly = new ShakkiAly(true, 2);
        GameState state = new GameState();
        String moves = "a2a4";
        state.setMoves(moves);
        state.playing = Side.WHITE;
        String mustanLiike = aly.nextMove(state);

        moves += ","+mustanLiike;
        state.setMoves(moves);
        state.playing = Side.BLACK;
        String valkoisenLiike = aly.nextMove(state);

        assertNotEquals(ShakkiAly.LUOVUTUS, mustanLiike);
        assertNotEquals(ShakkiAly.LUOVUTUS, valkoisenLiike);
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

        Pelilauta lauta = new Pelilauta(alku);

        assertTrue(ShakkiAly.laudanArvo(lauta, Side.WHITE) > 0);
        assertTrue(ShakkiAly.laudanArvo(lauta, Side.BLACK) < 0);
    }

}

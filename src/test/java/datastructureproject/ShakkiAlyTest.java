package datastructureproject;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.engine.GameState;
import chess.model.Side;

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
}

package datastructureproject;

import chess.bot.ChessBot;
import chess.engine.GameState;

public class ShakkiAly implements ChessBot {
    private int viimeSiirto = 1;

    @Override
    public String nextMove(GameState gamestate) {
        String lastMove = gamestate.getLatestMove();

        return null;
    }
    
}

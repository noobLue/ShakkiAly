package datastructureproject.luokat.apulaiset;

import chess.model.Side;

public class ShakkiApu {
    
    /**
     * Palauttaa kysytyn puolen pelaajan vastapelaajan puolen
     * @param puoli kysytty puoli
     * @return vastustajan puoli
     */
    public static Side vastustaja(Side puoli) {
        return puoli == Side.WHITE ? Side.BLACK : Side.WHITE;
    }
}

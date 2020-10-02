package datastructureproject.luokat;

import chess.model.Side;

public class ShakkiTemplaatti {
    /**
     * Kertoo kummalla puolella lautaa valkoinen pelaaja pelaa
     */
    public final boolean valkoinenAlhaalla = true;
    /**
     * "Universal Chess Interface" - protokollan merkiston (ie. 'r'=Rook) mukainen Shakkilaudan templaatti
     */
    private final char[][] aloitusTilanne = new char[][] {
        {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' }, 
        {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
        {'0', '0', '0', '0', '0', '0', '0', '0' }, 
        {'0', '0', '0', '0', '0', '0', '0', '0' }, 
        {'0', '0', '0', '0', '0', '0', '0', '0' }, 
        {'0', '0', '0', '0', '0', '0', '0', '0' }, 
        {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
        {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' }
    };

    public ShakkiTemplaatti() {

    }

    /**
     * Luo uuden templaatin
     * @param templaatti 2-dimensionaalinen array shakkinappuloita merkkeinä
     */
    public ShakkiTemplaatti(char[][] templaatti) {
        for (int y = 0; y < templaatti.length; y++) {
            for (int x = 0; x < templaatti[y].length; x++) {
                aloitusTilanne[y][x] = templaatti[y][x];
            }
        }
    }

    /**
     * 
     * @return templaatin koko
     */
    public int getKoko() {
        return aloitusTilanne.length;
    }

    /** 
     * @param y rivi, jota tarkastellaan
     * @return kumman puolen pelaajalle rivi kuuluu
     */
    public Side kummanRivi(final int y) {
        if (valkoinenAlhaalla) {
            return (y < aloitusTilanne[0].length / 2) ? Side.WHITE : Side.BLACK;
        } else {
            return (y >= aloitusTilanne[0].length / 2) ? Side.WHITE : Side.BLACK;
        }
    }

    public char[][] getTemplaatti() {
        return aloitusTilanne;
    }
}

package datastructureproject.luokat.evaluointi.osat;

import chess.model.Side;
import datastructureproject.luokat.nappulat.Kuningas;
import datastructureproject.luokat.nappulat.Kuningatar;
import datastructureproject.luokat.nappulat.Lahetti;
import datastructureproject.luokat.nappulat.Nappula;
import datastructureproject.luokat.nappulat.Ratsu;
import datastructureproject.luokat.nappulat.Sotilas;
import datastructureproject.luokat.nappulat.Torni;

public class RuutuPainotEval implements OsaEvaluaattori {
    private Side puoli;

    private static final double SKAALA = 250d;
    /**
     * https://www.chessprogramming.org/Simplified_Evaluation_Function#Piece-Square_Tables
    */
    private static final int[][] SOTILAS_PAINOT = new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0},
        {5, 10, 10, -20, -20, 10, 10, 5},
        {5, -5, -10, 0, 0, -10, -5, 5},
        {0, 0, 0, 20, 20, 0, 0, 0},
        {5, 5, 10, 25, 25, 10, 5, 5},
        {10, 10, 20, 30, 30, 20, 10, 10},
        {50, 50, 50, 50, 50, 50, 50, 50},
        {0, 0, 0, 0, 0, 0, 0, 0},
    };
    private static final int[][] RATSU_PAINOT = new int[][]{
        {-50, -40, -30, -30, -30, -30, -40, -50},
        {-40, -20,  0,  5,  5,  0, -20, -40},
        {-30, 5, 10, 15, 15, 10, 5, -30},
        {-30, 0, 15, 20, 20, 15, 0, -30},
        {-30, 5, 15, 20, 20, 15, 5, -30},
        {-30, 0, 10, 15, 15, 10, 0, -30},
        {-40, -20, 0, 0, 0, 0, -20, -40},
        {-50, -40, -30, -30, -30, -30, -40, -50},
    };
    private static final int[][] LAHETTI_PAINOT = new int[][]{
        {-20, -10, -10, -10, -10, -10, -10, -20},
        {-10,  5,  0,  0,  0,  0,  5, -10},
        {-10, 10, 10, 10, 10, 10, 10, -10},
        {-10,  0, 10, 10, 10, 10,  0, -10},
        {-10,  5,  5, 10, 10,  5,  5, -10},
        {-10,  0,  5, 10, 10,  5,  0, -10},
        {-10,  0,  0,  0,  0,  0,  0, -10},
        {-20, -10, -10, -10, -10, -10, -10, -20},
    };
    private static final int[][] TORNI_PAINOT = new int[][]{
        {0, 0, 0, 5, 5, 0, 0, 0},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {-5, 0, 0, 0, 0, 0, 0, -5},
        {5, 10, 10, 10, 10, 10, 10, 5},
        {0, 0, 0, 0, 0, 0, 0, 0},
    };
        
    private static final int[][] KUNINGATAR_PAINOT = new int[][]{
        {-20, -10, -10, -5, -5, -10, -10, -20},
        {-10, 0, 5, 0, 0, 0, 0, -10},
        {-10, 5, 5, 5, 5, 5, 0, -10},
        {0, 0, 5, 5, 5, 5, 0, -5},
        {-5, 0, 5, 5, 5, 5, 0, -5},
        {-10, 0, 5, 5, 5, 5, 0, -10},
        {-10, 0, 0, 0, 0, 0, 0, -10},
        {-20, -10, -10, -5, -5, -10, -10, -20},
    };
    private static final int[][] KUNINGAS_ALKU_PAINOT = new int[][]{
        {20, 30, 10,  0,  0, 10, 30, 20},
        {20, 20,  0,  0,  0,  0, 20, 20},
        {-10, -20, -20, -20, -20, -20, -20, -10},
        {-20, -30, -30, -40, -40, -30, -30, -20},
        {-30, -40, -40, -50, -50, -40, -40, -30},
        {-30, -40, -40, -50, -50, -40, -40, -30},
        {-30, -40, -40, -50, -50, -40, -40, -30},
        {-30, -40, -40, -50, -50, -40, -40, -30},
    };
    private static final int[][] KUNINGAS_LOPPU_PAINOT = new int[][]{
        {-50, -30, -30, -30, -30, -30, -30, -50},
        {-30, -30,  0,  0,  0,  0, -30, -30},
        {-30, -10, 20, 30, 30, 20, -10, -30},
        {-30, -10, 30, 40, 40, 30, -10, -30},
        {-30, -10, 30, 40, 40, 30, -10, -30},
        {-30, -10, 20, 30, 30, 20, -10, -30},
        {-30, -20, -10,  0,  0, -10, -20, -30},
        {-50, -40, -30, -20, -20, -30, -40, -50},
    };

    public RuutuPainotEval(Side puoli) {
        this.puoli = puoli;
    }


    private int arvo = 0;
    private int oSotilaat = 0;
    private int vSotilaat = 0;
    private Nappula oKuningas = null;
    private Nappula vKuningas = null;

    @Override
    public void prosessoiNappula(Nappula n) {
        int y = (n.getPuoli() == Side.WHITE ? n.getY() : 7 - n.getY());

        int tArvo = 0;
        if (n instanceof Sotilas) {
            tArvo += SOTILAS_PAINOT[y][n.getX()];
            if (puoli == n.getPuoli()) {
                oSotilaat++;
            } else {
                vSotilaat++;
            }
        } else if (n instanceof Ratsu) {
            tArvo += RATSU_PAINOT[y][n.getX()];
        } else if (n instanceof Lahetti) {
            tArvo += LAHETTI_PAINOT[y][n.getX()];
        } else if (n instanceof Torni) {
            tArvo += TORNI_PAINOT[y][n.getX()];
        } else if (n instanceof Kuningatar) {
            tArvo += KUNINGATAR_PAINOT[y][n.getX()];
        } else if (n instanceof Kuningas) {
            if (puoli == n.getPuoli()) {
                oKuningas = n;
            } else {
                vKuningas = n;
            }
        }

        arvo += (n.getPuoli() == puoli ? +1 : -1) * tArvo;
    }

    @Override
    public double getArvo() {
        if (oKuningas != null) {
            int yOma = (oKuningas.getPuoli() == Side.WHITE ? oKuningas.getY() : 7 - oKuningas.getY());
            if (oSotilaat > 4) {
                arvo += KUNINGAS_ALKU_PAINOT[yOma][oKuningas.getX()];
            } else {
                arvo += KUNINGAS_LOPPU_PAINOT[yOma][oKuningas.getX()];
            }
        }
        
        if (vKuningas != null) {
            int yVastus = (vKuningas.getPuoli() == Side.WHITE ? vKuningas.getY() : 7 - vKuningas.getY());
            if (vSotilaat > 4) {
                arvo -= KUNINGAS_ALKU_PAINOT[yVastus][vKuningas.getX()];
            } else {
                arvo -= KUNINGAS_LOPPU_PAINOT[yVastus][vKuningas.getX()];
            }
        }
        return arvo / SKAALA;
    }
    
}

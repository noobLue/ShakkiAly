package datastructureproject.evaluointi.osat;

import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.ShakkiAly;
import datastructureproject.luokat.evaluointi.osat.SotilaidenMaaraEval;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.model.Side;

public class SotilaidenMaaraEvalTest extends OsaEvalTest{

    @Test
    public void valkoisellaSotilaitaTest1() {
        char[][] alku = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', 'p', 'p', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        };

        Pelilauta lauta = new Pelilauta(alku);

        SotilaidenMaaraEval vEval = new SotilaidenMaaraEval(Side.WHITE);
        SotilaidenMaaraEval mEval = new SotilaidenMaaraEval(Side.BLACK);

        prosessoiNappulat(lauta, vEval);
        prosessoiNappulat(lauta, mEval);

        assertTrue(vEval.getArvo() > mEval.getArvo());
    }

    @Test
    public void mustallaSotilaitaTest1() {
        char[][] alku = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', 'P', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        };

        Pelilauta lauta = new Pelilauta(alku);

        SotilaidenMaaraEval vEval = new SotilaidenMaaraEval(Side.WHITE);
        SotilaidenMaaraEval mEval = new SotilaidenMaaraEval(Side.BLACK);

        prosessoiNappulat(lauta, vEval);
        prosessoiNappulat(lauta, mEval);

        assertTrue(vEval.getArvo() < mEval.getArvo());
    }

    @Test
    public void kummallakingSotilaitaTest1() {
        char[][] alku = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', 'p', 'p', 'p', 'p', 'p', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', 'P', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        };

        Pelilauta lauta = new Pelilauta(alku);

        SotilaidenMaaraEval vEval = new SotilaidenMaaraEval(Side.WHITE);
        SotilaidenMaaraEval mEval = new SotilaidenMaaraEval(Side.BLACK);

        prosessoiNappulat(lauta, vEval);
        prosessoiNappulat(lauta, mEval);

        assertEquals(vEval.getArvo(), mEval.getArvo(), ShakkiAly.DELTA);
    }

    @Test
    public void kummallakaanEiSotilaitaTest() {
        char[][] alku = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        };

        Pelilauta lauta = new Pelilauta(alku);

        SotilaidenMaaraEval vEval = new SotilaidenMaaraEval(Side.WHITE);
        SotilaidenMaaraEval mEval = new SotilaidenMaaraEval(Side.BLACK);

        prosessoiNappulat(lauta, vEval);
        prosessoiNappulat(lauta, mEval);

        assertEquals(vEval.getArvo(), mEval.getArvo(), ShakkiAly.DELTA);
    }
}

package datastructureproject.evaluointi.osat;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.evaluointi.osat.MateriaaliEval;

public class MateriaaliEvalTest extends OsaEvalTest{

    @Test
    public void omaPositiivinenTest1() {
        char[][] alku = new char[][]{
            { ' ', ' ', 'b', ' ', 'b', ' ', 'Q', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', 'p', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', 'P', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', 'B', ' ', ' ', 'q', 'B', ' ' },
        };

        Pelilauta lauta = new Pelilauta(alku);
        MateriaaliEval vEval = new MateriaaliEval(Side.WHITE);
        MateriaaliEval mEval = new MateriaaliEval(Side.BLACK);

        prosessoiNappulat(lauta, vEval);
        prosessoiNappulat(lauta, mEval);

        assertTrue(vEval.getArvo() > 0);
        assertTrue(mEval.getArvo() < 0);
    }

    @Test
    public void omaPositiivinenTest2() {
        char[][] alku = new char[][]{
            { ' ', 'q', 'b', ' ', 'b', ' ', 'Q', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', 'p', ' ', ' ', ' ' },
            { 'p', 'p', ' ', ' ', 'P', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { 'P', ' ', 'B', 'B', 'R', 'q', 'B', ' ' },
        };

        Pelilauta lauta = new Pelilauta(alku);
        MateriaaliEval vEval = new MateriaaliEval(Side.WHITE);
        MateriaaliEval mEval = new MateriaaliEval(Side.BLACK);

        prosessoiNappulat(lauta, vEval);
        prosessoiNappulat(lauta, mEval);

        assertTrue(vEval.getArvo() > 0);
        assertTrue(mEval.getArvo() < 0);
    }

    @Test
    public void yhtasuuretTest() {
        char[][] alku = new char[][]{
            { ' ', ' ', 'b', ' ', 'b', ' ', 'Q', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', 'p', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', 'P', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', 'B', ' ', ' ', 'q', 'B', ' ' },
        };

        Pelilauta lauta = new Pelilauta(alku);
        MateriaaliEval vEval = new MateriaaliEval(Side.WHITE);
        MateriaaliEval mEval = new MateriaaliEval(Side.BLACK);

        prosessoiNappulat(lauta, vEval);
        prosessoiNappulat(lauta, mEval);

        assertEquals(vEval.getArvo(), mEval.getArvo(), 0.0001d);
        assertEquals(0, vEval.getArvo(), 0.0001d);
    }

}

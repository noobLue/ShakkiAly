package datastructureproject.evaluointi.osat;

import static org.junit.Assert.*;
import org.junit.Test;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.ShakkiAly;
import datastructureproject.luokat.evaluointi.osat.LahettiParitEval;

public class LahettiParitEvalTest extends OsaEvalTest{
    @Test
    public void molemmatLahettiParitTest1(){
        char[][] alku = new char[][]{
            { ' ', ' ', 'b', ' ', 'b', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', 'B', ' ', ' ', ' ', 'B', ' ' },
        };

        Pelilauta lauta = new Pelilauta(alku);
        LahettiParitEval vEval = new LahettiParitEval(Side.WHITE);
        LahettiParitEval mEval = new LahettiParitEval(Side.WHITE);
        prosessoiNappulat(lauta, vEval);
        prosessoiNappulat(lauta, mEval);

        assertEquals(0, vEval.getArvo(), ShakkiAly.DELTA);
        assertEquals(0, mEval.getArvo(), ShakkiAly.DELTA);
    }

    @Test
    public void toinenLahettiParitTest1(){
        char[][] alku = new char[][]{
            { ' ', ' ', 'b', ' ', 'b', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', 'B', ' ' },
        };

        Pelilauta lauta = new Pelilauta(alku);
        LahettiParitEval vEval = new LahettiParitEval(Side.WHITE);
        LahettiParitEval mEval = new LahettiParitEval(Side.BLACK);
        prosessoiNappulat(lauta, vEval);
        prosessoiNappulat(lauta, mEval);

        assertEquals(1, vEval.getArvo(), ShakkiAly.DELTA);
        assertEquals(-1, mEval.getArvo(), ShakkiAly.DELTA);
    }

    @Test
    public void lahetitPoisTest1(){
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
        LahettiParitEval vEval = new LahettiParitEval(Side.WHITE);
        LahettiParitEval mEval = new LahettiParitEval(Side.BLACK);
        prosessoiNappulat(lauta, vEval);
        prosessoiNappulat(lauta, mEval);

        assertEquals(0, vEval.getArvo(), ShakkiAly.DELTA);
        assertEquals(0, mEval.getArvo(), ShakkiAly.DELTA);
    }
}

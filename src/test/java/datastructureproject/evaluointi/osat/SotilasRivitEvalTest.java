package datastructureproject.evaluointi.osat;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.evaluointi.osat.SotilasRivitEval;

public class SotilasRivitEvalTest extends OsaEvalTest{
    
    @Test
    public void tuplattuSotilasTest1() {
        char[][] alku1 = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        };
        char[][] alku2 = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', ' ', ' ', 'p', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        };

        Pelilauta lauta1 = new Pelilauta(alku1);
        Pelilauta lauta2 = new Pelilauta(alku2);

        SotilasRivitEval vEval1 = new SotilasRivitEval(Side.WHITE);
        SotilasRivitEval vEval2 = new SotilasRivitEval(Side.WHITE);

        prosessoiNappulat(lauta1, vEval1);
        prosessoiNappulat(lauta2, vEval2);

        assertTrue(vEval2.getArvo() > vEval1.getArvo());
    }

    @Test
    public void yksinainenSotilasTest1() {
        char[][] alku1 = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', ' ', ' ', 'P', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        };
        char[][] alku2 = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', 'P', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        };

        Pelilauta lauta1 = new Pelilauta(alku1);
        Pelilauta lauta2 = new Pelilauta(alku2);

        SotilasRivitEval mEval1 = new SotilasRivitEval(Side.BLACK);
        SotilasRivitEval mEval2 = new SotilasRivitEval(Side.BLACK);

        prosessoiNappulat(lauta1, mEval1);
        prosessoiNappulat(lauta2, mEval2);

        assertTrue(mEval2.getArvo() > mEval1.getArvo());
    }
}

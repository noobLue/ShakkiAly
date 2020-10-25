package datastructureproject.evaluointi.osat;

import static org.junit.Assert.*;
import org.junit.Test;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.ShakkiAly;
import datastructureproject.luokat.evaluointi.osat.RuutuPainotEval;

public class RuutuPainotEvalTest extends OsaEvalTest {
    @Test
    public void symmetriaToimiiTest1() {
        char[][] alkuValkoinen = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        };
        char[][] alkuMusta = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        };

        Pelilauta lautaValkoinen = new Pelilauta(alkuValkoinen);
        Pelilauta lautaMusta = new Pelilauta(alkuMusta);

        RuutuPainotEval vEval = new RuutuPainotEval(Side.WHITE);
        RuutuPainotEval mEval = new RuutuPainotEval(Side.BLACK);

        prosessoiNappulat(lautaValkoinen, vEval);
        prosessoiNappulat(lautaMusta, mEval);

        assertEquals(vEval.getArvo(), mEval.getArvo(), ShakkiAly.DELTA);
        
    }

    @Test
    public void kuningasAlkuLoppu() {
        char[][] alkuAlku = new char[][]{
            { 'q', ' ', ' ', 'k', ' ', ' ', ' ', 'q' },
            { ' ', 'p', 'p', 'p', 'p', 'p', 'p', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { 'q', ' ', ' ', ' ', ' ', ' ', ' ', 'q' },
        };
        char[][] alkuLoppu = new char[][]{
            { 'q', ' ', ' ', 'k', ' ', ' ', ' ', 'q' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { 'q', ' ', ' ', ' ', ' ', ' ', ' ', 'q' },
        };

        Pelilauta lautaAlku = new Pelilauta(alkuAlku);
        Pelilauta lautaLoppu = new Pelilauta(alkuLoppu);

        RuutuPainotEval evalAlku = new RuutuPainotEval(Side.WHITE);
        RuutuPainotEval evalLoppu = new RuutuPainotEval(Side.WHITE);
        
        prosessoiNappulat(lautaAlku, evalAlku);
        prosessoiNappulat(lautaLoppu, evalLoppu);

        System.out.println("alkuArvo: " + evalAlku.getArvo());
        System.out.println("loppuArvo: " + evalLoppu.getArvo());

        assertTrue(evalAlku.getArvo() >= evalLoppu.getArvo());
    }
}

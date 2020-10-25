package datastructureproject.evaluointi.osat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.evaluointi.osat.SotilasEstettyEval;

import static org.junit.Assert.*;

import org.junit.Test;

public class SotilasEstettyEvalTest extends OsaEvalTest {
    
    @Test
    public void estetynPuolenArvoOnPienempiTest() {
        char[][] alku = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', 'p', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'R', 'r', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', 'P', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        };

        Pelilauta lauta = new Pelilauta(alku);

        SotilasEstettyEval vEval = new SotilasEstettyEval(lauta, Side.WHITE);
        SotilasEstettyEval mEval = new SotilasEstettyEval(lauta, Side.BLACK);

        prosessoiNappulat(lauta, vEval);
        prosessoiNappulat(lauta, mEval);

        assertTrue(vEval.getArvo() > mEval.getArvo());

    }
}

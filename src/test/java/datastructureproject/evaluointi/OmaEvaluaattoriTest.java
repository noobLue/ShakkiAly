package datastructureproject.evaluointi;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.ShakkiAly;
import datastructureproject.luokat.evaluointi.Evaluaattori;
import datastructureproject.luokat.evaluointi.OmaEvaluaattori;

public class OmaEvaluaattoriTest {

    @Test
    public void symmetrinenArvoTest() {
        Pelilauta lauta = new Pelilauta(new char[][]{
            { ' ', ' ', ' ', 'k', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', 'p', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', 'P', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'K', ' ', ' ', ' ', ' ' },
        });

        Evaluaattori eval = new OmaEvaluaattori();

        assertEquals(eval.getLaudanArvo(lauta, Side.WHITE), eval.getLaudanArvo(lauta, Side.BLACK), ShakkiAly.DELTA);
    }

    @Test
    public void mustanTilanneParempiTest1() {
        Pelilauta lauta = new Pelilauta(new char[][]{
            { ' ', ' ', ' ', 'k', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', 'P', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'K', ' ', ' ', ' ', ' ' },
        });

        Evaluaattori eval = new OmaEvaluaattori();

        assertTrue(eval.getLaudanArvo(lauta, Side.WHITE) < eval.getLaudanArvo(lauta, Side.BLACK));
    }
    @Test
    public void valkoisenTilanneParempiTest1() {
        Pelilauta lauta = new Pelilauta(new char[][]{
            { ' ', ' ', ' ', 'k', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', 'p', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'K', ' ', ' ', ' ', ' ' },
        });

        Evaluaattori eval = new OmaEvaluaattori();
        System.out.println("valkoisen arvo: " + eval.getLaudanArvo(lauta, Side.WHITE) );
        System.out.println("mustan arvo: " + eval.getLaudanArvo(lauta, Side.BLACK) );

        assertTrue(eval.getLaudanArvo(lauta, Side.WHITE) > eval.getLaudanArvo(lauta, Side.BLACK));
    }

    @Test
    public void valkoisenTilanneParempiTest2() {
        Pelilauta lauta = new Pelilauta(new char[][]{
            { ' ', ' ', ' ', 'k', ' ', ' ', 'b', 'b' },
            { ' ', 'q', 'p', 'p', 'p', 'p', 'B', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', 'R', ' ', 'P', ' ', ' ', 'P', 'P' },
            { 'R', ' ', ' ', 'K', ' ', ' ', ' ', ' ' },
        });

        Evaluaattori eval = new OmaEvaluaattori();
        System.out.println("valkoisen arvo: " + eval.getLaudanArvo(lauta, Side.WHITE) );
        System.out.println("mustan arvo: " + eval.getLaudanArvo(lauta, Side.BLACK) );

        assertTrue(eval.getLaudanArvo(lauta, Side.WHITE) > eval.getLaudanArvo(lauta, Side.BLACK));
    }


    @Test
    public void valkoisenVoittoTilanne() {
        Pelilauta lauta = new Pelilauta(new char[][]{
            { ' ', ' ', ' ', 'k', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', 'p', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        });

        Evaluaattori eval = new OmaEvaluaattori();
        System.out.println("valkoisen arvo: " + eval.getLaudanArvo(lauta, Side.WHITE) );
        System.out.println("mustan arvo: " + eval.getLaudanArvo(lauta, Side.BLACK) );

        assertTrue(eval.getLaudanArvo(lauta, Side.WHITE) > eval.getLaudanArvo(lauta, Side.BLACK));
    }

    @Test
    public void mustanVoittoTilanne() {
        Pelilauta lauta = new Pelilauta(new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', 'p', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'K', ' ', ' ', ' ', ' ' },
        });

        Evaluaattori eval = new OmaEvaluaattori();
        System.out.println("valkoisen arvo: " + eval.getLaudanArvo(lauta, Side.WHITE) );
        System.out.println("mustan arvo: " + eval.getLaudanArvo(lauta, Side.BLACK) );

        assertTrue(eval.getLaudanArvo(lauta, Side.WHITE) < eval.getLaudanArvo(lauta, Side.BLACK));
    }


    @Test
    public void valkoisenVoittoTilanneTest2() {
        Pelilauta lauta = new Pelilauta(new char[][]{
            { ' ', ' ', ' ', 'k', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'p', 'p', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', 'Q', ' ', ' ', ' ', ' ', ' ' },
            { ' ', 'Q', ' ', 'Q', ' ', ' ', ' ', ' ' },
            { 'Q', ' ', ' ', 'P', 'Q', 'P', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', 'Q', ' ', 'R' },
        });

        Evaluaattori eval = new OmaEvaluaattori();
        System.out.println("valkoisen arvo: " + eval.getLaudanArvo(lauta, Side.WHITE) );
        System.out.println("mustan arvo: " + eval.getLaudanArvo(lauta, Side.BLACK) );

        assertTrue(eval.getLaudanArvo(lauta, Side.WHITE) > eval.getLaudanArvo(lauta, Side.BLACK));
        assertTrue(eval.getLaudanArvo(lauta, Side.WHITE) >= ShakkiAly.MAX / 2);
    }

    @Test
    public void mustanVoittoTilanneTest2() {
        Pelilauta lauta = new Pelilauta(new char[][]{
            { ' ', ' ', 'q', ' ', 'q', ' ', ' ', ' ' },
            { ' ', ' ', 'q', 'p', 'p', 'q', ' ', ' ' },
            { ' ', ' ', 'q', 'q', 'q', 'q', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'P', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'K', ' ', ' ', ' ', ' ' },
        });

        Evaluaattori eval = new OmaEvaluaattori();
        System.out.println("valkoisen arvo: " + eval.getLaudanArvo(lauta, Side.WHITE) );
        System.out.println("mustan arvo: " + eval.getLaudanArvo(lauta, Side.BLACK));

        assertTrue(eval.getLaudanArvo(lauta, Side.WHITE) < eval.getLaudanArvo(lauta, Side.BLACK));
        assertTrue(eval.getLaudanArvo(lauta, Side.BLACK) >= ShakkiAly.MAX / 2);
    }
}

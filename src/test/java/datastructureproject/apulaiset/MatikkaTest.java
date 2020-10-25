package datastructureproject.apulaiset;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import datastructureproject.luokat.apulaiset.Matikka;

public class MatikkaTest {
    
    @Test
    public void maksimiEkaPienempiTest1(){
        int a = 0;
        int b = 1;

        assertEquals(1, Matikka.maksimi(a, b));
    }

    @Test
    public void maksimiEkaPSuurempiTest1(){
        int a = 1;
        int b = 0;

        assertEquals(1, Matikka.maksimi(a, b));
    }

    @Test
    public void maksimiYhtasuuretTest1(){
        int a = 5;
        int b = 5;

        assertEquals(a, Matikka.maksimi(a, b));
        assertEquals(b, Matikka.maksimi(a, b));
    }

    @Test
    public void maksimiNegatiivinenSuurempi(){
        int a = 5;
        int b = -6;

        assertEquals(5, Matikka.maksimi(a, b));
    }

    @Test
    public void minimiEkaIsompiTest1(){
        int a = 0;
        int b = 1;

        assertEquals(0, Matikka.minimi(a, b));
    }

    @Test
    public void minimiEkaPienempiTest1(){
        int a = 1;
        int b = 0;

        assertEquals(0, Matikka.minimi(a, b));
    }

    @Test
    public void minimiYhtasuuretTest1(){
        int a = 1;
        int b = 1;

        assertEquals(1, Matikka.minimi(a, b));
    }

    @Test
    public void minimiNegatiivinenPienempi(){
        int a = -1;
        int b = 1;

        assertEquals(-1, Matikka.minimi(a, b));
    }

    @Test
    public void minimiNegatiivinenPienempi2(){
        int a = -1;
        int b = -31;

        assertEquals(-31, Matikka.minimi(a, b));
    }

    @Test
    public void maksimiJaMinimiSamatKunSamat(){
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;

        assertEquals(Matikka.minimi(a, b), Matikka.maksimi(a, b));
    }

    @Test
    public void negatiivisenItseisArvoPositiivinenTest1(){
        int a = Integer.MAX_VALUE;
        int b = -Integer.MAX_VALUE;

        assertEquals(a, Matikka.itseisarvo(b));
    }

    
    @Test
    public void positiivisenItseisarvoPositiivinenTest1(){
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;

        assertEquals(a, Matikka.itseisarvo(b));
    }


    @Test
    public void negatiivisenItseisarvoPositiivinenTest2(){
        assertEquals(Integer.MAX_VALUE, Matikka.itseisarvo(Integer.MIN_VALUE-1));
    }

    @Test
    public void nollanItseisarvoNolla1(){
        assertEquals(0, Matikka.itseisarvo(0));
        assertEquals(0, Matikka.itseisarvo(-0));
    }
}

package datastructureproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import datastructureproject.luokat.Ruutu;

public class RuutuTest {
    
    @Test 
    public void merkkiTest(){
        String str = "a1";
        Ruutu ruutu = new Ruutu(str);
        assertEquals(0, ruutu.getX());
        assertEquals(0, ruutu.getY());
    }

    @Test
    public void merkkiTest2(){
        String str = "h1";
        Ruutu ruutu = new Ruutu(str);
        assertEquals(7, ruutu.getX());
        assertEquals(0, ruutu.getY());
    }

    @Test
    public void indeksiJaMerkitVastaaTest(){
        Ruutu r = new Ruutu(3,1);
        Ruutu r2 = new Ruutu("d2");
        assertEquals(r.getX(), r2.getX());
        assertEquals(r.getY(), r2.getY());
    }

    @Test
    public void indeksiJaMerkitVastaaTest2(){
        Ruutu r = new Ruutu(4,2);
        Ruutu r2 = new Ruutu("d2");
        assertNotEquals(r.getX(), r2.getX());
        assertNotEquals(r.getY(), r2.getY());
    }

    @Test
    public void muutaXYTest(){
        Ruutu r = new Ruutu(0, 0);
        assertEquals(0, r.getX());
        assertEquals(0, r.getY());

        r.setX(1);
        r.setX(7);
        assertEquals(1, r.getX());
        assertEquals(7, r.getY());
    }
}

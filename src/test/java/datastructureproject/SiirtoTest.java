package datastructureproject;

import static org.junit.Assert.*;

import org.junit.Test;

import datastructureproject.luokat.Ruutu;
import datastructureproject.luokat.Siirto;

public class SiirtoTest {
    
    @Test 
    public void alkuKohdeTest(){
        Siirto siirto = new Siirto(new Ruutu("a3"), new Ruutu("b4"));

        assertEquals(0, siirto.getAlku().getX());
        assertEquals(2, siirto.getAlku().getY());
        assertEquals(1, siirto.getKohde().getX());
        assertEquals(3, siirto.getKohde().getY());
    }

    @Test
    public void ylennysTest(){
        Siirto siirto = new Siirto(new Ruutu("a1"), new Ruutu("a3"));
        assertFalse(siirto.onkoYlennysSiirto());
    }

    @Test
    public void ylennysTest2(){
        Siirto siirto = new Siirto(new Ruutu("a1"), new Ruutu("a3"), 'q');
        assertTrue(siirto.onkoYlennysSiirto());
    }

    @Test
    public void ylennysTest3(){
        Siirto siirto = new Siirto(0,0,1,1,'q');
        assertEquals("a1b2q", siirto.getUCIString());
    }
    
    @Test
    public void merkkijonoJasennysTest(){
        Siirto siirto = new Siirto("a1a7q");
        assertTrue(siirto.onkoYlennysSiirto());
        assertEquals(6, siirto.getKohde().getY());
    }

    @Test
    public void merkkijonoJasennysTest2(){
        Siirto siirto = new Siirto("a1a7");
        assertFalse(siirto.onkoYlennysSiirto());
        assertEquals(6, siirto.getKohde().getY());
    }

}

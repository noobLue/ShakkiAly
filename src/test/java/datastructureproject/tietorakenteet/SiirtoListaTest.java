package datastructureproject.tietorakenteet;

import static org.junit.Assert.*;
import java.lang.Math;

import org.junit.Test;
import datastructureproject.luokat.tietorakenteet.*;

public class SiirtoListaTest {
    
    @Test
    public void pieniListaOikeaKokoTest(){
        Siirto s = new Siirto("a2a3");
        Siirto s2 = new Siirto("a2a4");

        Lista<Siirto> lista = new Lista<Siirto>();
        lista.add(s);
        lista.add(s2);
        assertEquals(2, lista.size());
    }

    @Test
    public void isoListaOikeaKokoTest(){
        Siirto s = new Siirto("a2a3");
        int koko = 24;

        Lista<Siirto> lista = new Lista<Siirto>();
        for(int i = 0; i < koko; i++){
            lista.add(s);
        }
        assertEquals(koko, lista.size());
    }

    @Test
    public void tosiIsoListaOikeaKokoTest(){
        Siirto s = new Siirto("a2a3");
        int koko = (int)Math.pow((8*8), 3);

        Lista<Siirto> lista = new Lista<Siirto>();
        for(int i = 0; i < koko; i++){
            lista.add(s);
        }
        assertEquals(koko, lista.size());
    }

    @Test
    public void oikeellisuusTest1(){
        String string1 = "a2a3";
        String string2 = "a2a4";
        Siirto s = new Siirto(string1);
        Siirto s2 = new Siirto(string2);

        Lista<Siirto> lista = new Lista<Siirto>();
        lista.add(s);
        lista.add(s2);
        assertEquals(string1, lista.get(0).getUCIString());
        assertEquals(string2, lista.get(1).getUCIString());
    }

    @Test
    public void isoOikeellisuusTest(){
        String string1 = "a2a3";
        String string2 = "a2a4";
        Siirto s = new Siirto(string1);
        Siirto s2 = new Siirto(string2);

        Lista<Siirto> lista = new Lista<Siirto>();
        lista.add(s);
        for(int i = 0; i < 1999; i++){
            lista.add(s2);
        }
        lista.add(s);
        assertEquals(string1, lista.get(0).getUCIString());
        assertEquals(string2, lista.get(1).getUCIString());
        assertEquals(string2, lista.get(1999).getUCIString());
        assertEquals(string1, lista.get(2000).getUCIString());
    }

    @Test
    public void listanLisaysListaan(){
        Lista<Siirto> lista1 = new Lista<Siirto>();
        Lista<Siirto> lista2 = new Lista<Siirto>();

        lista1.add(new Siirto("b1a2"));
        lista1.add(new Siirto("b1a2"));
        lista1.add(new Siirto("b1a2"));
        lista1.add(new Siirto("b1a2"));
        lista1.add(new Siirto("b1a2"));
        lista1.add(new Siirto("b1a2"));

        lista2.addAll(lista1);
        assertEquals(lista1.size(), lista2.size());

        lista2.addAll(lista1);
        assertEquals(2 * lista1.size(), lista2.size());
    }
}

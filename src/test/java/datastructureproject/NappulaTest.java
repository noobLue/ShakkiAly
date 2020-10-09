package datastructureproject;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.tietorakenteet.*;
import datastructureproject.luokat.nappulat.Kuningas;
import datastructureproject.luokat.nappulat.Kuningatar;
import datastructureproject.luokat.nappulat.Lahetti;
import datastructureproject.luokat.nappulat.Nappula;
import datastructureproject.luokat.nappulat.Ratsu;
import datastructureproject.luokat.nappulat.Sotilas;
import datastructureproject.luokat.nappulat.Torni;

public class NappulaTest {
    @Test
    public void nappulaSotilasTest(){
        Nappula sotilasWhite = new Sotilas(Side.WHITE, new Ruutu("a1"));
        Nappula sotilasBlack = new Sotilas(Side.BLACK, new Ruutu("a8"));

        assertEquals(Side.BLACK, sotilasBlack.getPuoli());
        assertEquals(Side.WHITE, sotilasWhite.getPuoli());
        
        assertEquals(new Ruutu("a1").getAsString(), sotilasWhite.getRuutu().getAsString());
        assertEquals(new Ruutu("a8").getAsString(), sotilasBlack.getRuutu().getAsString());

        assertTrue(sotilasWhite instanceof Sotilas);
        assertTrue(sotilasBlack instanceof Sotilas);
    }

    @Test
    public void nappulaKuningasTest(){
        Nappula kuningasWhite = new Kuningas(Side.WHITE, new Ruutu("a1"));
        Nappula kuningasBlack = new Kuningas(Side.BLACK, new Ruutu("a8"));

        assertEquals(Side.BLACK, kuningasBlack.getPuoli());
        assertEquals(Side.WHITE, kuningasWhite.getPuoli());
        
        assertEquals(new Ruutu("a1").getAsString(), kuningasWhite.getRuutu().getAsString());
        assertEquals(new Ruutu("a8").getAsString(), kuningasBlack.getRuutu().getAsString());
        
        assertTrue(kuningasWhite instanceof Kuningas);
        assertTrue(kuningasBlack instanceof Kuningas);

        assertFalse(kuningasWhite instanceof Sotilas);
    }

    private static boolean asetteluTehty = false;
    private static Pelilauta lauta1;
    private static Pelilauta lauta2;
    @Before
    public void generoiLaudat(){
        if (asetteluTehty) {
            return;
        }

        lauta1 = new Pelilauta();

        lauta2 = new Pelilauta(new char[][]{
            { '0', 'n', 'b', '0', 'k', '0', '0', 'r' },
            { '0', '0', '0', 'q', '0', 'p', 'p', 'p' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', 'r', '0', 'p', '0', '0', 'n', '0' },

            { '0', '0', 'P', '0', '0', '0', 'P', '0' },
            { '0', '0', '0', '0', '0', '0', '0', 'P' },
            { '0', '0', 'P', 'P', 'P', 'P', 'P', '0' },
            { 'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' }
        });

        asetteluTehty = true;
    }

    public static boolean listaContains(SiirtoLista lista, Siirto siirto) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getUCIString().equals(siirto.getUCIString())) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void sotilasSiirrotTest1(){
        Nappula n = lauta1.getNappula(new Ruutu("a2"));
        //Varmistetaan että nappula on varmasti sotilas (eli templaatti on oikea)
        assertTrue(n instanceof Sotilas); 

        SiirtoLista lista = n.generoiSiirrot(lauta1);
        assertEquals(2, lista.size());
        
        Siirto siirto1 = new Siirto("a2a3");
        Siirto siirto2 = new Siirto("a2a4");
        assertTrue(listaContains(lista, siirto1));
        assertTrue(listaContains(lista, siirto2));
    }

    @Test
    public void sotilasSiirtoTest2() {
        Nappula n = lauta2.getNappula(new Ruutu("d4"));
        assertTrue(n instanceof Sotilas);
        SiirtoLista lista = n.generoiSiirrot(lauta2);
        assertEquals(2, lista.size());

        Siirto siirto1 = new Siirto("d4d5");
        Siirto siirto2 = new Siirto("d4c5");

        assertTrue(listaContains(lista, siirto1));
        assertTrue(listaContains(lista, siirto2));
    }

    @Test
    public void sotilasSiirtoYlennysTest() {
        Pelilauta ylennysLauta = new Pelilauta(new char[][]{
            { '0', 'n', 'b', '0', 'k', '0', '0', 'r' },
            { '0', '0', '0', 'q', '0', 'p', 'p', 'p' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', 'r', '0', 'p', '0', '0', 'n', '0' },

            { '0', '0', 'P', '0', '0', '0', 'P', '0' },
            { '0', '0', '0', '0', '0', '0', '0', 'P' },
            { 'p', '0', 'P', 'P', 'P', 'P', 'P', '0' },
            { '0', '0', 'B', 'Q', 'K', 'B', 'N', 'R' }
        });
        Nappula sotilas = ylennysLauta.getNappula(0, 6);
        assertTrue(sotilas instanceof Sotilas);

        assertTrue(listaContains(sotilas.generoiSiirrot(ylennysLauta), new Siirto("a7a8q")));
    }

    @Test
    public void ratsuSiirtoTest1(){
        Nappula n = lauta1.getNappula(new Ruutu("b1"));
        //Varmistetaan että templaatti on generoitunut oikein
        assertNull(lauta1.getNappula(new Ruutu("a3")));
        assertNull(lauta1.getNappula(new Ruutu("c3")));
        assertTrue(n instanceof Ratsu);

        SiirtoLista lista = n.generoiSiirrot(lauta1);
        
        assertTrue(listaContains(lista, new Siirto("b1a3")));
        assertTrue(listaContains(lista, new Siirto("b1c3")));
    }

    @Test
    public void ratsuSiirtoTest2(){
        Nappula n = lauta2.getNappula(new Ruutu("g4"));
        assertTrue(n instanceof Ratsu);

        SiirtoLista lista = n.generoiSiirrot(lauta2);
        assertEquals(4, lista.size());

        assertTrue(listaContains(lista, new Siirto("g4e3")));
        assertTrue(listaContains(lista, new Siirto("g4e5")));
        assertTrue(listaContains(lista, new Siirto("g4f6")));
        assertTrue(listaContains(lista, new Siirto("g4h6")));
        
    }

    @Test
    public void torniSiirtoTest1(){
        Nappula n = lauta1.getNappula(new Ruutu("a1"));
        assertTrue(n instanceof Torni);
        assertEquals(0, n.generoiSiirrot(lauta1).size());
    }

    @Test
    public void torniSiirtoTest2(){
        Nappula n = lauta2.getNappula(new Ruutu("b4"));
        assertTrue(n instanceof Torni);
        SiirtoLista lista = n.generoiSiirrot(lauta2);

        assertEquals(8, lista.size());
        assertTrue(listaContains(lista, new Siirto("b4b8")));
        assertTrue(listaContains(lista, new Siirto("b4b2")));


        assertFalse(listaContains(lista, new Siirto("b4b1")));
        assertFalse(listaContains(lista, new Siirto("b4d4")));
    }

    @Test
    public void lahettiSiirtoTest1(){
        Nappula n = lauta1.getNappula(new Ruutu("c1"));
        assertTrue(n instanceof Lahetti);
        assertEquals(0, n.generoiSiirrot(lauta1).size());
    }

    @Test
    public void lahettiSiirtoTest2(){
        Nappula n = lauta2.getNappula(new Ruutu("c1"));
        assertTrue(n instanceof Lahetti);
        SiirtoLista lista = n.generoiSiirrot(lauta2);

        assertEquals(2, lista.size());

        assertTrue(listaContains(lista, new Siirto("c1b2")));
        assertTrue(listaContains(lista, new Siirto("c1a3")));
    }

    @Test
    public void lahettiSiirtoTest3(){
        Pelilauta lautaTemp3 = lauta2.toteutaSiirto(new Siirto("c1b2"));

        Nappula n = lautaTemp3.getNappula(new Ruutu("b2"));
        assertTrue(n instanceof Lahetti);
        SiirtoLista lista = n.generoiSiirrot(lautaTemp3);

        assertEquals(4, lista.size());

        assertTrue(listaContains(lista, new Siirto("b2a1")));
        assertTrue(listaContains(lista, new Siirto("b2c1")));
        assertTrue(listaContains(lista, new Siirto("b2a3")));
        assertTrue(listaContains(lista, new Siirto("b2c3")));

    }

    @Test
    public void kuningatarSiirtoTest1(){
        Nappula n = lauta1.getNappula(new Ruutu("d1"));
        assertTrue(n instanceof Kuningatar);
        assertEquals(0, n.generoiSiirrot(lauta1).size());
    }

    @Test
    public void kuningatarSiirtoTest2(){
        Nappula n = lauta2.getNappula(new Ruutu("d2"));
        assertTrue(n instanceof Kuningatar);

        SiirtoLista lista = n.generoiSiirrot(lauta2);
        assertEquals(10, lista.size());

        assertTrue(listaContains(lista, new Siirto("d2g5")));
        assertTrue(listaContains(lista, new Siirto("d2d3")));

        assertFalse(listaContains(lista, new Siirto("d2h6")));
        assertFalse(listaContains(lista, new Siirto("d2b4")));
        assertFalse(listaContains(lista, new Siirto("d2a5")));
        assertFalse(listaContains(lista, new Siirto("d2d4")));
    }

    @Test
    public void kuningatarSiirtoTestSamatKuinLahettiTorni(){
        Nappula n = lauta2.getNappula(new Ruutu("d2"));
        assertTrue(n instanceof Kuningatar);

        SiirtoLista lista = n.generoiSiirrot(lauta2);

        Nappula torniSim = new Torni(Side.WHITE, new Ruutu("d2"));
        SiirtoLista torniLista = torniSim.generoiSiirrot(lauta2);
        Nappula lahettiSim = new Lahetti(Side.WHITE, new Ruutu("d2"));
        SiirtoLista lahettiLista = lahettiSim.generoiSiirrot(lauta2);

        assertEquals(torniLista.size()+lahettiLista.size(), lista.size());

        for(int i = 0; i < torniLista.size(); i++){
            assertTrue(listaContains(lista, torniLista.get(i)));
        }
        for(int i = 0; i < lahettiLista.size(); i++){
            assertTrue(listaContains(lista, lahettiLista.get(i)));
        }
    }

    @Test
    public void kuningasSiirtoTest1(){
        Nappula n = lauta1.getNappula(new Ruutu("e1"));
        assertTrue(n instanceof Kuningas);
        assertEquals(0, n.generoiSiirrot(lauta1).size());
    }

    @Test
    public void kuningasSiirtoTest2(){
        Nappula n = lauta2.getNappula(new Ruutu("e1"));
        assertTrue(n instanceof Kuningas);

        SiirtoLista lista = n.generoiSiirrot(lauta2);
        assertEquals(3, lista.size());

        assertTrue(listaContains(lista, new Siirto("e1d1")));
        assertTrue(listaContains(lista, new Siirto("e1e2")));
        assertTrue(listaContains(lista, new Siirto("e1f1"))); 

    }
}

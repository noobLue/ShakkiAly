package datastructureproject;

import org.junit.Test;

import chess.model.Side;

import static org.junit.Assert.*;

import datastructureproject.luokat.tietorakenteet.*;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.nappulat.Kuningas;
import datastructureproject.luokat.nappulat.Kuningatar;
import datastructureproject.luokat.nappulat.Lahetti;
import datastructureproject.luokat.nappulat.Ratsu;
import datastructureproject.luokat.nappulat.Sotilas;
import datastructureproject.luokat.nappulat.Torni;
import datastructureproject.luokat.nappulat.Nappula;

public class PelilautaTest {
    
    private boolean vertaaLautaaOdotettuun(Pelilauta lauta, char[][] odotettu){
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                Nappula n = lauta.getNappula(x, y);
                char c = odotettu[y][x];
                if(n == null && c != ' ' && c != '0')
                    return false;
                else if(n != null && n.getMerkki() != c)
                    return false;
            }
        }

        return true;
    }

    @Test
    public void alustusTest(){
        Pelilauta pLauta = new Pelilauta();

        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                char c = Pelilauta.ALKUTILANNE[y][x];
                if(c == 'p' || c == 'P'){
                    assertTrue(pLauta.lauta[y][x] instanceof Sotilas);
                } else if(c == 'r' || c == 'R'){
                    assertTrue(pLauta.lauta[y][x] instanceof Torni);
                } else if(c == 'q' || c == 'Q'){
                    assertTrue(pLauta.lauta[y][x] instanceof Kuningatar);
                } else if(c == 'k' || c == 'K'){
                    assertTrue(pLauta.lauta[y][x] instanceof Kuningas);
                } else if(c == 'n' || c == 'N'){
                    assertTrue(pLauta.lauta[y][x] instanceof Ratsu);
                } else if(c == 'b' || c == 'B'){
                    assertTrue(pLauta.lauta[y][x] instanceof Lahetti);
                } else if(c == '0' || c == ' '){
                    assertEquals(null, pLauta.lauta[y][x]);
                } else {
                    assertTrue(false);
                }
            }
        }
    }

    @Test
    public void siirtoTest(){
        Siirto siirto = new Siirto("e2e4");

        char[][] odotettu = new char[][]{
            { 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' },
            { 'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', 'p', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' },
            { 'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' }
        };

        Pelilauta lauta = new Pelilauta();
        lauta.siirto(siirto);

        assertTrue(vertaaLautaaOdotettuun(lauta, odotettu));
        lauta.peruutaSiirto();
        assertTrue(vertaaLautaaOdotettuun(lauta, Pelilauta.ALKUTILANNE));

    }

    
    @Test
    public void siirronGenerointiTest(){
        char[][] alku = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { 'K', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }
        };

        Pelilauta lauta = new Pelilauta(alku);

        assertEquals(3, lauta.generoiSiirrot(Side.WHITE).size());
        assertEquals(3, lauta.generoiSiirrot(Side.BLACK).size());
    }

    @Test
    public void siirronGenerointiTest2(){
        char[][] alku = new char[][]{
            { ' ', ' ', ' ', 'r', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }
        };

        Pelilauta lauta = new Pelilauta(alku);

        assertEquals(14, lauta.generoiSiirrot(Side.WHITE).size());
        assertEquals(0, lauta.generoiSiirrot(Side.BLACK).size());
    }

    @Test
    public void siirronGenerointiTestShakkejaEiFiltteroidaViela(){
        char[][] alku = new char[][]{
            { ' ', ' ', ' ', 'k', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', 'Q', ' ', 'Q', ' ', ' ', ' ' }
        };

        Pelilauta lauta = new Pelilauta(alku);

        assertEquals(5, lauta.generoiSiirrot(Side.WHITE).size());
    }

    @Test
    public void tornitusTest(){
        char[][] alku = new char[][]{
            { 'r', ' ', ' ', ' ', 'k', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', 'Q', ' ', 'K', ' ', ' ', 'K' }
        };

        char[][] kohde = new char[][]{
            { ' ', ' ', 'k', 'r', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', 'Q', ' ', 'K', ' ', ' ', 'K' }
        };


        Pelilauta lauta = new Pelilauta(alku);
        Siirto siirto = new Siirto("e1c1");
        lauta.siirto(siirto);
        assertTrue(vertaaLautaaOdotettuun(lauta, kohde));
        lauta.peruutaSiirto();
        assertTrue(vertaaLautaaOdotettuun(lauta, alku));
    }

    @Test
    public void ohestaLyontiTest(){
        char[][] alku = new char[][]{
            { 'r', ' ', ' ', ' ', 'k', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', 'p', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', 'P', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', 'Q', ' ', 'K', ' ', ' ', 'K' }
        };

        char[][] kohde = new char[][]{
            { 'r', ' ', ' ', ' ', 'k', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', 'Q', ' ', 'K', ' ', ' ', 'K' }
        };


        Pelilauta lauta = new Pelilauta(alku);
        
        lauta.siirto(new Siirto("f5f4"));
        lauta.siirto(new Siirto("g2g4"));
        lauta.siirto(new Siirto("f4g3"));
        assertTrue(vertaaLautaaOdotettuun(lauta, kohde));

        lauta.peruutaSiirto();
        lauta.peruutaSiirto();
        lauta.peruutaSiirto();
        assertTrue(vertaaLautaaOdotettuun(lauta, alku));
    }

    @Test
    public void kaksoisAskelTest(){
        Pelilauta lauta = new Pelilauta();
        Lista<Siirto> siirrot = lauta.generoiSiirrot(Side.WHITE);
        
        boolean contains1 = false;
        boolean contains2 = false;
        for(int i = 0; i < siirrot.size(); i++){
            Siirto s = siirrot.get(i);
            System.out.println(s.getUCIString());
            if(s.getUCIString().equals("a2a4")){
                contains1 = true;
            } else if(s.getUCIString().equals("h2h4")){
                contains2 = true;
            }
        }
        assertTrue(contains1);
        assertTrue(contains2);
    }

    @Test 
    public void ylennysTest(){
        char[][] alku = new char[][]{
            { ' ', ' ', ' ', 'p', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
        };
        Pelilauta lauta = new Pelilauta(alku);
        for(int i = 0; i < 6; i++){
            lauta.siirto(new Siirto(new Ruutu(3,i), new Ruutu(3,i+1)));
        }
        
        lauta.siirto(new Siirto("d7d8q"));
        assertTrue(lauta.getNappula(new Ruutu("d8")) instanceof Kuningatar);
        lauta.peruutaSiirto();

        lauta.siirto(new Siirto("d7d8n"));
        assertTrue(lauta.getNappula(new Ruutu("d8")) instanceof Ratsu);
        lauta.peruutaSiirto();

        lauta.siirto(new Siirto("d7d8b"));
        assertTrue(lauta.getNappula(new Ruutu("d8")) instanceof Lahetti);
        lauta.peruutaSiirto();

        lauta.siirto(new Siirto("d7d8r"));
        assertTrue(lauta.getNappula(new Ruutu("d8")) instanceof Torni);
        lauta.peruutaSiirto();

    }

    @Test
    public void generoiduissaLiikkeissaEiOleDuplikaatteja(){
        Pelilauta lauta = new Pelilauta();
        Lista<Siirto> siirrot = lauta.generoiSiirrot(Side.WHITE);
        for (int i = 0; i < siirrot.size(); i++){
            for (int j = 0; j < siirrot.size(); j++){
                if (i == j) {
                    continue;
                }
                assertNotEquals(siirrot.get(j).getUCIString(), siirrot.get(i).getUCIString());
            }
        }
    }

    @Test
    public void siirronPeruutusTest1() {
        Pelilauta lauta = new Pelilauta();

        lauta.siirto(new Siirto("a2a4"));
        lauta.siirto(new Siirto("c7c5"));
        lauta.siirto(new Siirto("a1a3") );
        lauta.siirto(new Siirto("d8a5"));

        assertTrue(vertaaLautaaOdotettuun(lauta, new char[][] {
            {' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r' }, 
            {' ', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
            {'r', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'Q', ' ', 'P', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'P', 'P', ' ', 'P', 'P', 'P', 'P', 'P' }, 
            {'R', 'N', 'B', ' ', 'K', 'B', 'N', 'R' }
        }));
        lauta.peruutaSiirto();
        assertTrue(vertaaLautaaOdotettuun(lauta, new char[][] {
            {' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r' }, 
            {' ', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
            {'r', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'P', 'P', ' ', 'P', 'P', 'P', 'P', 'P' }, 
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' }
        }));
        lauta.peruutaSiirto();
        assertTrue(vertaaLautaaOdotettuun(lauta, new char[][] {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' }, 
            {' ', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'P', 'P', ' ', 'P', 'P', 'P', 'P', 'P' }, 
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' }
        }));
        lauta.peruutaSiirto();
        assertTrue(vertaaLautaaOdotettuun(lauta, new char[][] {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' }, 
            {' ', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' }, 
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' }
        }));
        lauta.peruutaSiirto();
        assertTrue(vertaaLautaaOdotettuun(lauta, new char[][] {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' }, 
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' }, 
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' }
        }));
    }

    @Test
    public void tornituksenPeruutus() {
        char[][] alku = new char[][] {
            {'r', ' ', ' ', ' ', 'k', 'b', 'n', 'r' }, 
            {' ', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'Q', ' ', 'P', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'P', 'P', ' ', 'P', 'P', 'P', 'P', 'P' }, 
            {'R', 'N', 'B', ' ', 'K', 'B', 'N', 'R' }
        };
        Pelilauta lauta = new Pelilauta(alku);

        lauta.siirto(new Siirto("e1c1"));
        assertTrue(vertaaLautaaOdotettuun(lauta, new char[][] {
            {' ', ' ', 'k', 'r', ' ', 'b', 'n', 'r' }, 
            {' ', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'Q', ' ', 'P', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            {'P', 'P', ' ', 'P', 'P', 'P', 'P', 'P' }, 
            {'R', 'N', 'B', ' ', 'K', 'B', 'N', 'R' }
        }));
        lauta.peruutaSiirto();
        assertTrue(vertaaLautaaOdotettuun(lauta, alku));
    }

    @Test
    public void syonninPeruutusTest() {
        char[][] alku = new char[][] {
            {'r', ' ', ' ', ' ', 'k', 'b', 'n', 'r' }, 
            {' ', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'Q', ' ', 'P', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'P', 'P', ' ', 'P', 'P', 'P', 'P', 'P' }, 
            {'R', 'N', 'B', ' ', 'K', 'B', 'N', 'R' }
        };
        Pelilauta lauta = new Pelilauta(alku);

        lauta.siirto(new Siirto("a5d2"));
        assertTrue(vertaaLautaaOdotettuun(lauta, new char[][] {
            {'r', ' ', ' ', ' ', 'k', 'b', 'n', 'r' }, 
            {' ', 'p', 'p', 'Q', 'p', 'p', 'p', 'p' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' ' }, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
            {'P', 'P', ' ', 'P', 'P', 'P', 'P', 'P' }, 
            {'R', 'N', 'B', ' ', 'K', 'B', 'N', 'R' }
        }));
        lauta.peruutaSiirto();
        assertTrue(vertaaLautaaOdotettuun(lauta, alku));
    }
}

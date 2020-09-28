package datastructureproject;

import org.junit.Test;

import chess.model.Side;

import static org.junit.Assert.*;

import datastructureproject.luokat.*;
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
                if(n == null && c != '0')
                    return false;
                else if(n != null && n.toString().charAt(0) != c)
                    return false;
            }
        }

        return true;
    }

    @Test
    public void alustusTest(){
        ShakkiTemplaatti templaatti = new ShakkiTemplaatti();
        Pelilauta pLauta = new Pelilauta(templaatti);

        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                if(templaatti.getTemplaatti()[y][x] == 'p'){
                    assertTrue(pLauta.lauta[y][x] instanceof Sotilas);
                } else if(templaatti.getTemplaatti()[y][x] == 'r'){
                    assertTrue(pLauta.lauta[y][x] instanceof Torni);
                } else if(templaatti.getTemplaatti()[y][x] == 'q'){
                    assertTrue(pLauta.lauta[y][x] instanceof Kuningatar);
                } else if(templaatti.getTemplaatti()[y][x] == 'k'){
                    assertTrue(pLauta.lauta[y][x] instanceof Kuningas);
                } else if(templaatti.getTemplaatti()[y][x] == 'n'){
                    assertTrue(pLauta.lauta[y][x] instanceof Ratsu);
                } else if(templaatti.getTemplaatti()[y][x] == 'b'){
                    assertTrue(pLauta.lauta[y][x] instanceof Lahetti);
                } else if(templaatti.getTemplaatti()[y][x] == '0'){
                    assertEquals(null, pLauta.lauta[y][x]);
                } else {
                    assertTrue(false);
                }
            }
        }
    }

    @Test
    public void siirtoTest(){
        ShakkiTemplaatti templaatti = new ShakkiTemplaatti();
        Siirto siirto = new Siirto("e2e4");

        char[][] odotettu = new char[][]{
            { 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' },
            { 'p', 'p', 'p', 'p', '0', 'p', 'p', 'p' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', 'p', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' },
            { 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' }
        };

        Pelilauta lauta = new Pelilauta(templaatti);
        Pelilauta tulos = lauta.toteutaSiirto(siirto);

        assertTrue(vertaaLautaaOdotettuun(tulos, odotettu));

    }

    
    @Test
    public void siirronGenerointiTest(){
        char[][] alku = new char[][]{
            { '0', '0', '0', '0', '0', '0', '0', 'k' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { 'k', '0', '0', '0', '0', '0', '0', '0' }
        };

        Pelilauta lauta = new Pelilauta(new ShakkiTemplaatti(alku));

        assertEquals(3, lauta.kaikkiLiikeet(Side.WHITE).size());
        assertEquals(3, lauta.kaikkiLiikeet(Side.BLACK).size());
    }

    @Test
    public void siirronGenerointiTest2(){
        char[][] alku = new char[][]{
            { '0', '0', '0', 'r', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' }
        };

        Pelilauta lauta = new Pelilauta(new ShakkiTemplaatti(alku));

        assertEquals(14, lauta.kaikkiLiikeet(Side.WHITE).size());
        assertEquals(0, lauta.kaikkiLiikeet(Side.BLACK).size());
    }

    @Test
    public void siirronGenerointiTestShakkejaEiFiltteroidaViela(){
        char[][] alku = new char[][]{
            { '0', '0', '0', 'k', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', 'q', '0', 'q', '0', '0', '0' }
        };

        Pelilauta lauta = new Pelilauta(new ShakkiTemplaatti(alku));

        assertEquals(5, lauta.kaikkiLiikeet(Side.WHITE).size());
    }

    @Test
    public void tornitusTest(){
        char[][] alku = new char[][]{
            { 'r', '0', '0', '0', 'k', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', 'q', '0', 'k', '0', '0', 'k' }
        };

        char[][] kohde = new char[][]{
            { '0', '0', 'k', 'r', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', 'q', '0', 'k', '0', '0', 'k' }
        };


        Pelilauta lauta = new Pelilauta(new ShakkiTemplaatti(alku));
        Siirto siirto = new Siirto("e1c1");

        assertTrue(vertaaLautaaOdotettuun(lauta.toteutaSiirto(siirto), kohde));
    }

    @Test
    public void enPassantTest(){
        char[][] alku = new char[][]{
            { 'r', '0', '0', '0', 'k', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', 'p', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', 'p', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', 'q', '0', 'k', '0', '0', 'k' }
        };

        char[][] kohde = new char[][]{
            { 'r', '0', '0', '0', 'k', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', 'p', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', 'q', '0', 'k', '0', '0', 'k' }
        };


        Pelilauta lauta = new Pelilauta(new ShakkiTemplaatti(alku));
        
        lauta = lauta.toteutaSiirto(new Siirto("f5f4"));
        lauta = lauta.toteutaSiirto(new Siirto("g2g4"));
        lauta = lauta.toteutaSiirto(new Siirto("f4g3"));

        assertTrue(vertaaLautaaOdotettuun(lauta, kohde));
    }

    @Test
    public void kaksoisAskelTest(){
        Pelilauta lauta = new Pelilauta(new ShakkiTemplaatti());
        SiirtoLista siirrot = lauta.kaikkiLiikeet(Side.WHITE);
        
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
            { '0', '0', '0', 'p', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
            { '0', '0', '0', '0', '0', '0', '0', '0' },
        };
        Pelilauta lauta = new Pelilauta(new ShakkiTemplaatti(alku));
        for(int i = 0; i < 6; i++){
            lauta = lauta.toteutaSiirto(new Siirto(new Ruutu(3,i), new Ruutu(3,i+1)));
        }
        
        Pelilauta kuningatarLauta = lauta.toteutaSiirto(new Siirto("d7d8q"));
        Pelilauta ratsuLauta = lauta.toteutaSiirto(new Siirto("d7d8n"));
        Pelilauta lahettiLauta = lauta.toteutaSiirto(new Siirto("d7d8b"));
        Pelilauta torniLauta = lauta.toteutaSiirto(new Siirto("d7d8r"));

        assertTrue(kuningatarLauta.getNappula(new Ruutu("d8")) instanceof Kuningatar);
        assertTrue(ratsuLauta.getNappula(new Ruutu("d8")) instanceof Ratsu);
        assertTrue(lahettiLauta.getNappula(new Ruutu("d8")) instanceof Lahetti);
        assertTrue(torniLauta.getNappula(new Ruutu("d8")) instanceof Torni);
    }

    @Test
    public void generoiduissaLiikkeissaEiOleDuplikaatteja(){
        Pelilauta lauta = new Pelilauta(new ShakkiTemplaatti());
        SiirtoLista siirrot = lauta.kaikkiLiikeet(Side.WHITE);
        for (int i = 0; i < siirrot.size(); i++){
            for (int j = 0; j < siirrot.size(); j++){
                if (i == j) {
                    continue;
                }
                assertNotEquals(siirrot.get(j).getUCIString(), siirrot.get(i).getUCIString());
            }
        }
    }
}

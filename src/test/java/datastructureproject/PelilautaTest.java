package datastructureproject;

import org.junit.Test;
import static org.junit.Assert.*;

import datastructureproject.luokat.*;
import datastructureproject.luokat.nappulat.Kuningas;
import datastructureproject.luokat.nappulat.Kuningatar;
import datastructureproject.luokat.nappulat.Lahetti;
import datastructureproject.luokat.nappulat.Ratsu;
import datastructureproject.luokat.nappulat.Sotilas;
import datastructureproject.luokat.nappulat.Torni;

public class PelilautaTest {
    
    @Test
    public void templaattiXYTest(){
        int y = 0;
        int x = 0;
        assertEquals('r', Pelilauta.TEMPLAATTI[y][x]);
        assertEquals('r', Pelilauta.TEMPLAATTI[y][x+7]);
        assertEquals('r', Pelilauta.TEMPLAATTI[y+7][x+7]);
        assertEquals('r', Pelilauta.TEMPLAATTI[y+7][x+7]);

        assertEquals('n', Pelilauta.TEMPLAATTI[y][x+1]);
        assertEquals('0', Pelilauta.TEMPLAATTI[y+4][x]);

        assertEquals('k', Pelilauta.TEMPLAATTI[y][4]);
        assertEquals('k', Pelilauta.TEMPLAATTI[y+7][4]);
    }

    @Test
    public void alustusTest(){
        Pelilauta pLauta = new Pelilauta();
        pLauta.alusta();

        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                if(Pelilauta.TEMPLAATTI[y][x] == 'p'){
                    assertTrue(pLauta.lauta[y][x] instanceof Sotilas);
                } else if(Pelilauta.TEMPLAATTI[y][x] == 'r'){
                    assertTrue(pLauta.lauta[y][x] instanceof Torni);
                } else if(Pelilauta.TEMPLAATTI[y][x] == 'q'){
                    assertTrue(pLauta.lauta[y][x] instanceof Kuningatar);
                } else if(Pelilauta.TEMPLAATTI[y][x] == 'k'){
                    assertTrue(pLauta.lauta[y][x] instanceof Kuningas);
                } else if(Pelilauta.TEMPLAATTI[y][x] == 'n'){
                    assertTrue(pLauta.lauta[y][x] instanceof Ratsu);
                } else if(Pelilauta.TEMPLAATTI[y][x] == 'b'){
                    assertTrue(pLauta.lauta[y][x] instanceof Lahetti);
                } else if(Pelilauta.TEMPLAATTI[y][x] == '0'){
                    assertEquals(null, pLauta.lauta[y][x]);
                } else {
                    assertTrue(false);
                }
            }
        }
    }

}

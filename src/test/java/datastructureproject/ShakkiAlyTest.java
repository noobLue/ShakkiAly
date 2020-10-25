package datastructureproject;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.engine.GameState;
import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.ShakkiAly;
import datastructureproject.luokat.nappulat.Torni;
import datastructureproject.luokat.tietorakenteet.Lista;
import datastructureproject.luokat.tietorakenteet.Ruutu;
import datastructureproject.luokat.tietorakenteet.Siirto;
import datastructureproject.luokat.tietorakenteet.Tupla;

public class ShakkiAlyTest {

    @Test 
    public void yesMan(){
        assertTrue(true);
    }

    @Test
    public void sallitunLiikkeenGenerointi(){
        ShakkiAly aly = new ShakkiAly(true, 1);
        GameState state = new GameState();
        String moves = "a2a4";
        state.setMoves(moves);
        state.playing = Side.BLACK;
        String mustanLiike = aly.nextMove(state);

        moves += ","+mustanLiike;
        state.setMoves(moves);
        state.playing = Side.WHITE;
        String valkoisenLiike = aly.nextMove(state);

        assertNotNull(mustanLiike);
        assertNotNull(valkoisenLiike);
    }

    @Test
    public void sallitunLiikkeenGenerointi2(){
        ShakkiAly aly = new ShakkiAly(true, 2);
        GameState state = new GameState();
        String moves = "a2a4";
        state.setMoves(moves);
        state.playing = Side.WHITE;
        System.out.println("latestMove: " + state.getLatestMove());
        String mustanLiike = aly.nextMove(state);
        System.out.println("mliike: " + mustanLiike);

        moves += ","+mustanLiike;
        state.setMoves(moves);
        state.playing = Side.BLACK;
        System.out.println(state.getLatestMove());
        String valkoisenLiike = aly.nextMove(state);

        assertNotNull(mustanLiike);
        assertNotNull(valkoisenLiike);
    }

    @Test
    public void lopputilanteenEtsinta() {
        char[][] alku = new char[][]{
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k' },
            { ' ', ' ', ' ', ' ', ' ', ' ', 'p', 'p' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'r' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { 'P', ' ', 'P', ' ', ' ', ' ', ' ', ' ' },
            { 'P', 'K', 'P', 'P', 'P', 'P', 'P', 'P' },
            { 'R', 'N', 'B', 'R', 'R', 'R', 'R', 'R' },
        };

        Pelilauta lauta = new Pelilauta(alku);
        ShakkiAly aly = new ShakkiAly(false, 1);
        assertTrue(lauta.getNappula(new Ruutu("h3")) instanceof Torni);
        assertSame(Side.WHITE, lauta.getNappula(new Ruutu("h3")).getPuoli());

        Siirto voittosiirto = new Siirto("h3b3");
        lauta.siirto(voittosiirto);
        assertTrue(lauta.onMatissa(Side.BLACK));
        lauta.peruutaSiirto();

        Siirto valkoisenVoittoSiirto = new Siirto("h3b3"); 

        Tupla<Double, Siirto> arvoSiirto = aly.negaMax(lauta, Side.WHITE);
    
        assertEquals(valkoisenVoittoSiirto.getUCIString(), arvoSiirto.getToka().getUCIString());
    }

    @Test
    public void miniMaxLoytaaShakkiMatinTest1() {
        Pelilauta lauta = new Pelilauta(new char [][]{
            { ' ', ' ', ' ', 'k', ' ', 'r', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', 'q', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
            { ' ', ' ', ' ', 'K', ' ', ' ', ' ', ' ' },
        });
        lauta.siirto(new Siirto("f1f7"));
        lauta.siirto(new Siirto("d8e8"));
        lauta.siirto(new Siirto("g2g8"));
        assertTrue(lauta.onMatissa(Side.BLACK));
        lauta.peruutaSiirto();
        lauta.peruutaSiirto();
        lauta.peruutaSiirto();


        ShakkiAly aly = new ShakkiAly(false, 3);

        Tupla<Double,Siirto> tupla = aly.negaMax(lauta, Side.WHITE);
        assertTrue(tupla.getEka() >= ShakkiAly.MAX - ShakkiAly.DELTA);
        System.out.println("Siirto: " + tupla.getEka() + ", " + tupla.getToka());
        lauta.siirto(tupla.getToka());

        lauta.siirto(new Siirto("d8e8"));
        System.out.println("Siirto: d8e8");
        
        Tupla<Double,Siirto> tupla2 = aly.negaMax(lauta, Side.WHITE);
        assertTrue(tupla2.getEka() >= ShakkiAly.MAX - ShakkiAly.DELTA);
        System.out.println("Siirto: " + tupla2.getEka() + ", " + tupla2.getToka());
        lauta.siirto(tupla2.getToka());

        assertTrue(lauta.onMatissa(Side.BLACK));
    }

}

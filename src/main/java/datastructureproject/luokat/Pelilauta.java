package datastructureproject.luokat;

import chess.model.Side;
import datastructureproject.luokat.nappulat.Kuningas;
import datastructureproject.luokat.nappulat.Kuningatar;
import datastructureproject.luokat.nappulat.Lahetti;
import datastructureproject.luokat.nappulat.Nappula;
import datastructureproject.luokat.nappulat.Ratsu;
import datastructureproject.luokat.nappulat.Sotilas;
import datastructureproject.luokat.nappulat.Torni;

public class Pelilauta {
    /**
     * Kertoo kummalla puolella lautaa valkoinen pelaaja pelaa
     */
    public static final boolean VALKOINEN_ALAPUOLELLA = true;
    /**
     * "Universal Chess Interface" - protokollan merkiston (ie. 'r'=Rook) mukainen Shakkilaudan templaatti
     */
    public static final char[][] TEMPLAATTI = new char[][]{
        { 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' },
        { 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' },
        { '0', '0', '0', '0', '0', '0', '0', '0' },
        { '0', '0', '0', '0', '0', '0', '0', '0' },
        { '0', '0', '0', '0', '0', '0', '0', '0' },
        { '0', '0', '0', '0', '0', '0', '0', '0' },
        { 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' },
        { 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' }
    };
    
    /** 
     * @param y rivi, jota tarkastellaan
     * @return kumman puolen pelaajalle rivi kuuluu
     */
    private static Side kummanRivi(int y){
        if(VALKOINEN_ALAPUOLELLA){
            return (y < TEMPLAATTI[0].length / 2) ? Side.WHITE : Side.BLACK;
        } else {
            return (y >= TEMPLAATTI[0].length / 2) ? Side.WHITE : Side.BLACK;
        }
    }
    
    /**
     * Kuvaa shakkilaudan tilannetta
     */
    public final Nappula[][] lauta = new Nappula[TEMPLAATTI.length][TEMPLAATTI[0].length];

    /**
     * Alustaa pelilaudan templaatin mukaiseen alkuasentoon
     */
    public void alusta(){
        for(int y = 0; y < TEMPLAATTI.length; y++){
            for(int x = 0; x < TEMPLAATTI[0].length; x++){
                switch(TEMPLAATTI[y][x]){
                    case 'r':
                        lauta[y][x] = new Torni(kummanRivi(y), new Ruutu(x,y));
                        break;
                    case 'n':
                        lauta[y][x] = new Ratsu(kummanRivi(y), new Ruutu(x,y));
                        break;
                    case 'b':
                        lauta[y][x] = new Lahetti(kummanRivi(y), new Ruutu(x,y));
                        break;
                    case 'q':
                        lauta[y][x] = new Kuningatar(kummanRivi(y), new Ruutu(x,y));
                        break;
                    case 'k':
                        lauta[y][x] = new Kuningas(kummanRivi(y), new Ruutu(x,y));
                        break;
                    case 'p':
                        lauta[y][x] = new Sotilas(kummanRivi(y), new Ruutu(x,y));
                        break;
                    default:
                        break;
                }

            }
        }
    }
}

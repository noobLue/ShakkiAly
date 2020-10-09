package datastructureproject.luokat.nappulat;


import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.tietorakenteet.*;

public class Kuningatar extends Nappula {
    private final int[][] suunnat = new int[][]{
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, //Viistorivit
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}    //Vaakarivit
    };


    public Kuningatar(Side puoli, Ruutu ruutu) {
        super('q', puoli, ruutu, 8);
    }

    public Nappula kopioi() {
        return new Kuningatar(getPuoli(), getRuutu().kopioi());
    }


    @Override
    public SiirtoLista generoiSiirrot(Pelilauta lauta) {
        return generoiSuoratSiirrot(lauta, suunnat);
    }

    @Override
    public String toString() {
        return "q";
    }
}

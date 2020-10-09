package datastructureproject.luokat.nappulat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.tietorakenteet.*;

public class Lahetti extends Nappula {
    private final int[][] suunnat = new int[][]{
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    public Lahetti(Side puoli, Ruutu ruutu) {
        super('b', puoli, ruutu, 6);
    }

    public Nappula kopioi() {
        return new Lahetti(getPuoli(), getRuutu().kopioi());
    }

    public SiirtoLista generoiSiirrot(Pelilauta lauta) {
        return generoiSuoratSiirrot(lauta, suunnat);
    }

    
    @Override
    public String toString() {
        return "b";
    }

}

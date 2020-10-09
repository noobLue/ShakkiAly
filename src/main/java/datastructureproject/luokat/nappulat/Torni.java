package datastructureproject.luokat.nappulat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.tietorakenteet.*;

public class Torni extends Nappula {
    private final int[][] suunnat = new int[][]{
        {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public Torni(Side puoli, Ruutu ruutu) {
        super('r', puoli, ruutu, 3);
    }

    public Nappula kopioi() {
        return new Torni(getPuoli(), getRuutu().kopioi());
    }

    public SiirtoLista generoiSiirrot(Pelilauta lauta) {
        return generoiSuoratSiirrot(lauta, suunnat);
    }

    @Override
    public String toString() {
        return "r";
    }
}

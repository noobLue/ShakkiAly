package datastructureproject.luokat.nappulat;

import java.util.ArrayList;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.Ruutu;
import datastructureproject.luokat.Siirto;

public class Ratsu extends Nappula {

    public Ratsu(Side puoli, Ruutu ruutu) {
        super(puoli, ruutu);
    }

    public Nappula kopioi() {
        return new Ratsu(getPuoli(), getRuutu().kopioi());
    }


    @Override
    public ArrayList<Siirto> kaikkiSiirrot(Pelilauta lauta) {
        ArrayList<Siirto> siirrot = new ArrayList();
        int[][] siirrotArr = new int[][] {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

        for (int[] pari: siirrotArr) {
            Ruutu ruutu = new Ruutu(getX() + pari[0], getY() + pari[1]);
            if (!ruutu.olenLaudalla(lauta)) {
                continue;
            }
            Nappula n = lauta.lauta[ruutu.getY()][ruutu.getX()];
            if (n == null || n.getPuoli() != this.getPuoli()) {
                siirrot.add(new Siirto(getX(), getY(), ruutu.getX(), ruutu.getY()));
            }
        }
       

        return siirrot;
    }

    @Override
    public String toString() {
        return "n";
    }

}

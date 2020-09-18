package datastructureproject.luokat.nappulat;

import java.util.ArrayList;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.Ruutu;
import datastructureproject.luokat.Siirto;

public class Kuningas extends Nappula {
    public Kuningas(Side puoli, Ruutu ruutu) {
        super(puoli, ruutu);
    }

    public Nappula kopioi() {
        return new Kuningas(getPuoli(), getRuutu().kopioi());
    }

    @Override
    public ArrayList<Siirto> kaikkiSiirrot(Pelilauta lauta) {
        ArrayList<Siirto> siirrot = new ArrayList<Siirto>();

        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                if (y == 0 && x == 0) {
                    continue;
                }
                Ruutu r = new Ruutu(getX() + x, getY() + y);
                if (!r.olenLaudalla(lauta)) {
                    continue;
                }
                Nappula n = lauta.lauta[r.getY()][r.getX()];
                if (n == null || n.getPuoli() != this.getPuoli()) {
                    siirrot.add(new Siirto(getX(), getY(), r.getX(), r.getY()));
                }
            }
        }
        //Tornitus?

        return siirrot;
    }

    @Override
    public String toString() {
        return "k";
    }
}

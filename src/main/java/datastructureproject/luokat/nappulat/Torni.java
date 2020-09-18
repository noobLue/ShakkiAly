package datastructureproject.luokat.nappulat;

import java.util.ArrayList;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.Ruutu;
import datastructureproject.luokat.Siirto;

public class Torni extends Nappula {
    public Torni(Side puoli, Ruutu ruutu) {
        super(puoli, ruutu);
    }

    public Nappula kopioi() {
        return new Torni(getPuoli(), getRuutu().kopioi());
    }

    public ArrayList<Siirto> kaikkiSiirrot(Pelilauta lauta) {
        ArrayList<Siirto> siirrot = new ArrayList<>();
        for (int x = getX() + 1; x < lauta.getKoko(); x++) {
            Nappula n = lauta.getNappula(x, getY());
            if (n == null) {
                siirrot.add(new Siirto(getX(), getY(), x, getY()));
            } else {
                if (n.getPuoli() != getPuoli()) {
                    siirrot.add(new Siirto(getX(), getY(), x, getY()));
                }
                break;
            }
        }

        for (int x = getX() - 1; x >= 0; x--) {
            Nappula n = lauta.getNappula(x, getY());
            if (n == null) {
                siirrot.add(new Siirto(getX(), getY(), x, getY()));
            } else {
                if (n.getPuoli() != getPuoli()) {
                    siirrot.add(new Siirto(getX(), getY(), x, getY()));
                }
                break;
            }
        }

        for (int y = getY() + 1; y < lauta.getKoko(); y++) {
            Nappula n = lauta.getNappula(getX(), y);
            if (n == null) {
                siirrot.add(new Siirto(getX(), getY(), getX(), y));
            } else {
                if (n.getPuoli() != getPuoli()) {
                    siirrot.add(new Siirto(getX(), getY(), getX(), y));
                }
                break;
            }
        }

        for (int y = getY() - 1; y >= 0; y--) {
            Nappula n = lauta.getNappula(getX(), y);
            if (n == null) {
                siirrot.add(new Siirto(getX(), getY(), getX(), y));
            } else {
                if (n.getPuoli() != getPuoli()) {
                    siirrot.add(new Siirto(getX(), getY(), getX(), y));
                }
                break;
            }
        }
        
        return siirrot;
    }

    @Override
    public String toString() {
        return "r";
    }
}

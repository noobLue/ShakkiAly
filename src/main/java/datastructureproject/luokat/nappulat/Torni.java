package datastructureproject.luokat.nappulat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.Ruutu;
import datastructureproject.luokat.Siirto;
import datastructureproject.luokat.SiirtoLista;

public class Torni extends Nappula {
    public Torni(Side puoli, Ruutu ruutu) {
        super(puoli, ruutu);
        this.arvo = 3;
        this.merkki = 'r';
    }

    public Nappula kopioi() {
        return new Torni(getPuoli(), getRuutu().kopioi());
    }

    public SiirtoLista kaikkiSiirrot(Pelilauta lauta) {
        SiirtoLista siirrot = new SiirtoLista();
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

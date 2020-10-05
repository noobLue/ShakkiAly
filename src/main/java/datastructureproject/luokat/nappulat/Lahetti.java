package datastructureproject.luokat.nappulat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.Ruutu;
import datastructureproject.luokat.Siirto;
import datastructureproject.luokat.SiirtoLista;

public class Lahetti extends Nappula {
    public Lahetti(Side puoli, Ruutu ruutu) {
        super(puoli, ruutu);
        this.arvo = 6;
        this.merkki = 'b';
    }

    public Nappula kopioi() {
        return new Lahetti(getPuoli(), getRuutu().kopioi());
    }

    public SiirtoLista kaikkiSiirrot(Pelilauta lauta) {
        SiirtoLista siirrot = new SiirtoLista();
        for (int i = 1; getX() + i < lauta.getKoko() && getY() + i < lauta.getKoko(); i++) {
            Ruutu kohde1 = new Ruutu(getX() + i, getY() + i);
            Nappula n = lauta.getNappula(kohde1.getX(), kohde1.getY());
            if (n == null) {
                siirrot.add(new Siirto(getX(), getY(), kohde1.getX(), kohde1.getY()));
            } else {
                if (n.getPuoli() != getPuoli()) {
                    siirrot.add(new Siirto(getX(), getY(), kohde1.getX(), kohde1.getY()));
                }
                break;
            }
        }

        for (int i = 1; getX() + i < lauta.getKoko() && getY() - i >= 0; i++) {
            Ruutu kohde1 = new Ruutu(getX() + i, getY() - i);
            Nappula n = lauta.getNappula(kohde1.getX(), kohde1.getY());
            if (n == null) {
                siirrot.add(new Siirto(getX(), getY(), kohde1.getX(), kohde1.getY()));
            } else {
                if (n.getPuoli() != getPuoli()) {
                    siirrot.add(new Siirto(getX(), getY(), kohde1.getX(), kohde1.getY()));
                }
                break;
            }
        }

        for (int i = 1; getX() - i >= 0 && getY() + i < lauta.getKoko(); i++) {
            Ruutu kohde1 = new Ruutu(getX() - i, getY() + i);
            Nappula n = lauta.getNappula(kohde1.getX(), kohde1.getY());
            if (n == null) {
                siirrot.add(new Siirto(getX(), getY(), kohde1.getX(), kohde1.getY()));
            } else {
                if (n.getPuoli() != getPuoli()) {
                    siirrot.add(new Siirto(getX(), getY(), kohde1.getX(), kohde1.getY()));
                }
                break;
            }
        }

        for (int i = 1; getX() - i >= 0 && getY() - i >= 0; i++) {
            Ruutu kohde1 = new Ruutu(getX() - i, getY() - i);
            Nappula n = lauta.getNappula(kohde1.getX(), kohde1.getY());
            if (n == null) {
                siirrot.add(new Siirto(getX(), getY(), kohde1.getX(), kohde1.getY()));
            } else {
                if (n.getPuoli() != getPuoli()) {
                    siirrot.add(new Siirto(getX(), getY(), kohde1.getX(), kohde1.getY()));
                }
                break;
            }
        }

        return siirrot;
    }

    
    @Override
    public String toString() {
        return "b";
    }

}

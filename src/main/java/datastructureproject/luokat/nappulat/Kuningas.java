package datastructureproject.luokat.nappulat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.tietorakenteet.*;

public class Kuningas extends Nappula {
    public Kuningas(Side puoli, Ruutu ruutu) {
        super('k', puoli, ruutu);
    }

    
    public Nappula kopioi() {
        return new Kuningas(getPuoli(), getRuutu().kopioi());
    }

    @Override
    public Lista<Siirto> generoiSiirrot(Pelilauta lauta) {
        Lista<Siirto> siirrot = new Lista<>();

        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                Ruutu ruutu;
                if ((y == 0 && x == 0) || !(ruutu = new Ruutu(getX() + x, getY() + y)).olenLaudalla(lauta)) {
                    continue;
                }
                Nappula n = lauta.lauta[ruutu.getY()][ruutu.getX()];
                if (n == null || n.getPuoli() != this.getPuoli()) {
                    siirrot.add(new Siirto(getX(), getY(), ruutu.getX(), ruutu.getY()));
                }
            }
        }

        return siirrot;
    }

    @Override
    public String toString() {
        return "k";
    }
}

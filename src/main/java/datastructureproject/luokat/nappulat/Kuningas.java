package datastructureproject.luokat.nappulat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.Ruutu;
import datastructureproject.luokat.Siirto;
import datastructureproject.luokat.SiirtoLista;

public class Kuningas extends Nappula {
    public Kuningas(Side puoli, Ruutu ruutu) {
        super(puoli, ruutu);
        this.arvo = Integer.MAX_VALUE / 2;
        this.merkki = 'k';
    }

    public Nappula kopioi() {
        return new Kuningas(getPuoli(), getRuutu().kopioi());
    }

    @Override
    public SiirtoLista kaikkiSiirrot(Pelilauta lauta) {
        SiirtoLista siirrot = new SiirtoLista();

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


        return siirrot;
    }

    @Override
    public String toString() {
        return "k";
    }
}

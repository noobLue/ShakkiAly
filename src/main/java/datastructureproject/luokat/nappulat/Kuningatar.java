package datastructureproject.luokat.nappulat;

import java.util.ArrayList;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.Ruutu;
import datastructureproject.luokat.Siirto;

public class Kuningatar extends Nappula {
    public Kuningatar(Side puoli, Ruutu ruutu) {
        super(puoli, ruutu);
    }

    public Nappula kopioi() {
        return new Kuningatar(getPuoli(), getRuutu().kopioi());
    }


    @Override
    public ArrayList<Siirto> kaikkiSiirrot(Pelilauta lauta) {
        ArrayList<Siirto> siirrot = new ArrayList<>();
        Torni tTorni = new Torni(getPuoli(), getRuutu());
        Lahetti tLahetti = new Lahetti(getPuoli(), getRuutu());

        siirrot.addAll(tTorni.kaikkiSiirrot(lauta));
        siirrot.addAll(tLahetti.kaikkiSiirrot(lauta));

        return siirrot;
    }

    @Override
    public String toString() {
        return "q";
    }
}

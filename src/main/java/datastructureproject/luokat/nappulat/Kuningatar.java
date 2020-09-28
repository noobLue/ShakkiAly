package datastructureproject.luokat.nappulat;


import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.Ruutu;
import datastructureproject.luokat.SiirtoLista;

public class Kuningatar extends Nappula {
    public Kuningatar(Side puoli, Ruutu ruutu) {
        super(puoli, ruutu);
        this.arvo = 8;
    }

    public Nappula kopioi() {
        return new Kuningatar(getPuoli(), getRuutu().kopioi());
    }


    @Override
    public SiirtoLista kaikkiSiirrot(Pelilauta lauta) {
        SiirtoLista siirrot = new SiirtoLista();
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

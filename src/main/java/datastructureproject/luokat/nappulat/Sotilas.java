package datastructureproject.luokat.nappulat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.tietorakenteet.*;

public class Sotilas extends Nappula {
    public Sotilas(Side puoli, Ruutu ruutu) {
        super('p', puoli, ruutu);
    }

    public Nappula kopioi() {
        return new Sotilas(getPuoli(), getRuutu().kopioi());
    }

    @Override
    public Lista<Siirto> generoiSiirrot(Pelilauta lauta) {
        Lista<Siirto> siirrot = new Lista<>();

        //Liiku yksi eteen
        Ruutu yksiEteen = new Ruutu(getX(), getEteenpainY(1));
        if (yksiEteen.olenLaudalla(lauta) && lauta.getNappula(yksiEteen) == null) {
            boolean ylennys = yksiEteen.getY() == (getPuoli() == Side.WHITE ? lauta.getKoko() - 1 : 0);
            if (ylennys) {
                //Lisätään listaan kuningattareksi sekä ratsuksi ylennys, 
                //torni ja lähetti olisivat turhia kuningattareen verrattuna.
                siirrot.add(new Siirto(getX(), getY(), yksiEteen.getX(), yksiEteen.getY(), 'q'));
                siirrot.add(new Siirto(getX(), getY(), yksiEteen.getX(), yksiEteen.getY(), 'n'));
            } else {
                siirrot.add(new Siirto(getX(), getY(), yksiEteen.getX(), yksiEteen.getY()));
            }

            //Liiku kaksi eteen (yksiEteen pitää olla myös validi kohta liikkua)
            Ruutu kaksiEteen = new Ruutu(getX(), getEteenpainY(2));
            if (kaksiEteen.olenLaudalla(lauta) && lauta.getNappula(kaksiEteen) == null 
                    && getY() == (getPuoli() == Side.WHITE ? 1 : lauta.getKoko() - 2)) {
                siirrot.add(new Siirto(getX(), getY(), kaksiEteen.getX(), kaksiEteen.getY()));
            }
        }

        //Syö viistoon
        int[] viistot = new int[] {1, -1};
        for (int x: viistot) {
            Ruutu vRuutu = new Ruutu(getX() + x, getEteenpainY(1));
            if (!vRuutu.olenLaudalla(lauta)) {
                continue;
            }
            Nappula vNappula = lauta.lauta[vRuutu.getY()][vRuutu.getX()];
            if (vNappula != null && vNappula.getPuoli() != this.getPuoli()) {

                boolean ylennys = vRuutu.getY() == (getPuoli() == Side.WHITE ? lauta.getKoko() - 1 : 0);
                if (ylennys) {
                    //Lisätään listaan kuningattareksi sekä ratsuksi ylennys, 
                    //torni ja lähetti olisivat turhia kuningattareen verrattuna.
                    siirrot.add(new Siirto(getRuutu().kopioi(), vRuutu.kopioi(), 'q'));
                    siirrot.add(new Siirto(getRuutu().kopioi(), vRuutu.kopioi(), 'n'));
                } else {
                    siirrot.add(new Siirto(getRuutu().kopioi(), vRuutu.kopioi()));
                }
            }
        }

        return siirrot;
    }

    @Override
    public String toString() {
        return "p";
    }

}

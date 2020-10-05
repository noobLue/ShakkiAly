package datastructureproject.luokat.nappulat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.Ruutu;
import datastructureproject.luokat.Siirto;
import datastructureproject.luokat.SiirtoLista;

public class Sotilas extends Nappula {
    public Sotilas(Side puoli, Ruutu ruutu) {
        super(puoli, ruutu);
        this.arvo = 1;
        this.merkki = 'p';
    }

    public Nappula kopioi() {
        return new Sotilas(getPuoli(), getRuutu().kopioi());
    }

    @Override
    public SiirtoLista kaikkiSiirrot(Pelilauta lauta) {
        SiirtoLista siirrot = new SiirtoLista();

        //Liiku yksi eteen
        Ruutu yksiEteen = new Ruutu(getX(), getEteenpainY(1));
        if (yksiEteen.olenLaudalla(lauta) && lauta.getNappula(yksiEteen) == null) {
            boolean ylennys = yksiEteen.getY() == (getPuoli() == Side.WHITE ? lauta.getKoko() - 1 : 0);
            if (ylennys) {
                siirrot.add(new Siirto(getX(), getY(), yksiEteen.getX(), yksiEteen.getY(), 'q'));
            } else {
                siirrot.add(new Siirto(getX(), getY(), yksiEteen.getX(), yksiEteen.getY()));
            }

            //Liiku kaksi eteen (yksiEteen pitää olla myös validi kohta)
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
                siirrot.add(new Siirto(getX(), getY(), vRuutu.getX(), vRuutu.getY()));
            }
        }

        //En passant

        
        return siirrot;
    }

    @Override
    public String toString() {
        return "p";
    }

}

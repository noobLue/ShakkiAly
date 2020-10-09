package datastructureproject.luokat.nappulat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.apulaiset.ShakkiApu;
import datastructureproject.luokat.tietorakenteet.*;

/**
 * Kuvaa shakkinappulaa
 */
public abstract class Nappula {
    private Ruutu ruutu;
    protected Side puoli;
    protected char merkki;
    protected int arvo;

    /**
     * 
     * @param puoli kumpi pelaaja omistaa taman nappulan
     * @param ruutu nappulan sijainti pelilaudalla
     */
    public Nappula(char merkki, Side puoli, Ruutu ruutu, int arvo) {
        this.merkki = merkki;
        this.puoli = puoli;
        this.ruutu = ruutu;
        this.arvo = arvo;
    }

    public Side getPuoli() {
        return puoli;
    }

    public int getX() {
        return ruutu.getX();
    }

    public int getY() {
        return ruutu.getY();
    }

    public void setRuutu(int x, int y) {
        this.ruutu.setX(x);
        this.ruutu.setY(y);
    }

    public Ruutu getRuutu() {
        return ruutu;
    }

    /**
     * Palauttaa nappulan y-akselin sijainnnin, kun sitä liikutetaan eteenpäin y-askelta
     * (toimii molemmilla puolilla)
     * 
     * @param y y-akselin askeleiden määrä
     * @return y-akselin koordinaatti
     */
    protected int getEteenpainY(int y) {
        return getRuutu().getEteenpainY(getPuoli(), y);
    }

    /**
     * 
     * @param lauta pelilaudan tilanne
     * @return mahdolliset siirrot tässä pelitilanteessa
     */
    public abstract SiirtoLista generoiSiirrot(Pelilauta lauta);

    /**
     * Palauttaa nappulan sallitut siirrot, kun se saa liikkua tiettyihin suuntiin loputtomasti. 
     * Tiettyyn suuntaan siirtojen etsiminen lopetetaan, kun mennään pelilaudan reunan yli
     * tai päädytään syömään vastustajan nappula. 
     * 
     * @param lauta pelitilanne pelilautana
     * @param suunnat suunnat joihin nappula saa liikkua
     * @return lista siirroista, joita nappula saa liikkua annettuihin suuntiin
     */
    protected SiirtoLista generoiSuoratSiirrot(Pelilauta lauta, int[][] suunnat) {
        SiirtoLista siirrot = new SiirtoLista();
        for (int[] pari: suunnat) {
            Ruutu ruutu = getRuutu().kopioi();
            ruutu.addX(pari[0]);
            ruutu.addY(pari[1]);
            while (ruutu.olenLaudalla(lauta)) {
                Nappula n = lauta.getNappula(ruutu);
                if (n == null) {
                    siirrot.add(new Siirto(getRuutu(), ruutu.kopioi()));
                } else {
                    if (n.getPuoli() != getPuoli()) {
                        siirrot.add(new Siirto(getRuutu(), ruutu.kopioi()));
                    }
                    break;
                }
                
                ruutu.addX(pari[0]);
                ruutu.addY(pari[1]);
            }
        }
        return siirrot;
    }

    /**
     * Kopioi nappulan, niin että kopio ja alkuperäinen eivät vaikuta toisiinsa (deep copy)
     * @return kopio tästä nappulasta
     */
    public abstract Nappula kopioi();

    /**
     * 
     * 
     * @param lauta katseltava pelitilanne
     * @return onko tämä nappula uhattuna tässä tilanteessa
     */
    public boolean olenUhattuna(Pelilauta lauta) {
        SiirtoLista vastustajanLiikkeet = lauta.generoiSiirrot(ShakkiApu.vastustaja(getPuoli()));

        for (int i = 0; i < vastustajanLiikkeet.size(); i++) {
            Siirto s = vastustajanLiikkeet.get(i);
            if (s.getKohde().getX() == getX() &&  s.getKohde().getY() == getY()) {
                return true;
            }
        }

        return false;
    }

    /**
     * 
     * @return palauttaa nappulalle määritetyn arvon, jota käytetään pelitilanteen arvioinnissa (heurestiikassa).
     */
    public int getArvo() {
        return arvo;
    }

    public char getMerkki() {
        return this.getPuoli() == Side.WHITE ? merkki : versitaaliksi(merkki);
    }

    private static char versitaaliksi(char c) {
        switch (c) {
            case 'r':
                return 'R';
            case 'n':
                return 'N';
            case 'b':
                return 'B';
            case 'q':
                return 'Q';
            case 'k':
                return 'K';
            case 'p':
                return 'P';
            default:
                return '0';
        }
    }
    
}

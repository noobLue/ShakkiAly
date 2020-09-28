package datastructureproject.luokat.nappulat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.Ruutu;
import datastructureproject.luokat.Siirto;
import datastructureproject.luokat.SiirtoLista;

/**
 * Kuvaa shakkinappulaa
 */
public abstract class Nappula {
    private Ruutu ruutu;
    protected Side puoli;

    /**
     * 
     * @param puoli kumpi pelaaja omistaa taman nappulan
     * @param ruutu nappulan sijainti pelilaudalla
     */
    public Nappula(Side puoli, Ruutu ruutu) {
        this.puoli = puoli;
        this.ruutu = ruutu;
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
    public abstract SiirtoLista kaikkiSiirrot(Pelilauta lauta);

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
        SiirtoLista vastustajanLiikkeet = lauta.kaikkiLiikeet(getPuoli() == Side.WHITE ? Side.BLACK : Side.WHITE);
        for (int i = 0; i < vastustajanLiikkeet.size(); i++) {
            Siirto s = vastustajanLiikkeet.get(i);
            if (s.getKohde().getX() == getX() &&  s.getKohde().getY() == getY()) {
                return true;
            }
        }

        return false;
    }

    protected int arvo = 1;

    /**
     * 
     * @return palauttaa nappulalle määritetyn arvon, jota käytetään pelitilanteen arvioinnissa (heurestiikassa).
     */
    public int getArvo() {
        return arvo;
    }
}

package datastructureproject.luokat.tietorakenteet;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;

/**
 * Kuvaa shakkiruutua sijaintina
 */
public class Ruutu {
    private final char alkuMerkki = 'a';
    private int x;
    private int y;

    /**
     * 
     * @param x shakkiruudun vaakatason sijainti indeksina alkaen nollasta.
     * @param y shakkiruudun pystytason sijainti indeksina alkaen nollasta.
     */
    public Ruutu(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * 
     * @param pos UCI protokollan mukainen representaatio shakkiruudusta
     */
    public Ruutu(final String pos) { //ex. "a1"
        x = (int) pos.toLowerCase().charAt(0) - (int) alkuMerkki;
        y = Integer.parseInt(pos.substring(1, 2)) - 1;
    }

    public Ruutu kopioi() {
        return new Ruutu(x, y);
    }

    /**
     * Miten tämä ruutu esitettäisiin, jos se olisi osana UCI-muotoista siirtoa
     * @return shakkiruudun merkkijono-muotoinen esitys
     */
    public String getAsString() {
        char eka = (char) ((int) alkuMerkki + x);
        int toka = y + 1;
        return "" + eka + toka;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void addX(int x) {
        this.x += x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public void addY(int y) {
        this.y += y;
    }
    
    /**
     * Kertoo onko ruutu sallittu ruutu laudalla. 
     * @param lauta lauta jota tutkitaan
     * @return onko ruutu laudan dimensioiden sisällä.
     */
    public boolean olenLaudalla(Pelilauta lauta) {
        return getX() >= 0 && getY() >= 0 && getX() < lauta.getKoko() && getY() < lauta.getKoko();
    }

    /**
     * Palauttaa nappulan y-akselin sijainnnin, kun sitä liikutetaan eteenpäin y-askelta
     * (toimii molemmilla puolilla)
     * 
     * @param puoli kumman puolen pelaajan kannalta katsotaan suuntaa
     * @param y y-akselin askeleiden määrä
     * @return y-akselin koordinaatti
     */
    public int getEteenpainY(Side puoli, int y) {
        return getY() + (puoli == Side.WHITE ? y : -y);
    }

    @Override
    public String toString() {
        return getAsString();
    }
    
}

package datastructureproject.luokat;

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

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }
    
    /**
     * Kertoo onko ruutu sallittu ruutu laudalla. 
     * @param lauta lauta jota tutkitaan
     * @return onko ruutu laudan dimensioiden sisällä.
     */
    public boolean olenLaudalla(Pelilauta lauta) {
        return getX() >= 0 && getY() >= 0 && getX() < lauta.getKoko() && getY() < lauta.getKoko();
    }

    @Override
    public String toString() {
        return getAsString();
    }
    
}

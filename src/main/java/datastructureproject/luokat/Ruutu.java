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
    public Ruutu(final int x, final int y){
        this.x = x;
        this.y = y;
    }
    /**
     * 
     * @param pos UCI protokollan mukainen representaatio shakkiruudusta
     */
    public Ruutu(final String pos){ //ex. "a1"
        x = (int) pos.toLowerCase().charAt(0) - (int) alkuMerkki;
        y = Integer.parseInt(pos.substring(1,2)) - 1;
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
    
}

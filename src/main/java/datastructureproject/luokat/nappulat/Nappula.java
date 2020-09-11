package datastructureproject.luokat.nappulat;

import chess.model.Side;
import datastructureproject.luokat.Ruutu;

/**
 * Kuvaa shakkinappulaa
 */
public abstract class Nappula {
    private Ruutu ruutu;
    protected Side puoli;

    /**
     * 
     * @param puoli kumpi pelaaja omistaa taman nappulan
     * @param positio nappulan sijainti pelilaudalla
     */
    public Nappula(Side puoli, Ruutu positio){
        this.puoli = puoli;
        this.ruutu = positio;
    }

    public Side getPuoli(){
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

    public Ruutu getRuutu(){
        return ruutu;
    }
}

package datastructureproject.luokat.evaluointi.osat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.nappulat.Nappula;
import datastructureproject.luokat.nappulat.Sotilas;

public class SotilasEstettyEval implements OsaEvaluaattori {
    private static final double PAINOARVO = 0.5d;
    private Pelilauta lauta;
    private Side puoli;
    private double arvo;

    public SotilasEstettyEval(Pelilauta lauta, Side puoli) {
        this.lauta = lauta;
        this.puoli = puoli;
    }

    @Override
    public void prosessoiNappula(Nappula n) {
        //Katsotaan onko suoraan sotilaan edessä olevassa ruudussa jokin nappula, 
        //joka estää sotilaan etenemisen
        int eteenYksiY = n.getRuutu().getEteenpainY(n.getPuoli(), 1);
        if (eteenYksiY >= 0 
                && eteenYksiY < Pelilauta.ALKUTILANNE.length
                && n instanceof Sotilas
                && lauta.getNappula(n.getX(), eteenYksiY) != null) {
            arvo -= (n.getPuoli() == puoli ? +1 : -1) * PAINOARVO;
        }
    }

    @Override
    public double getArvo() {
        return arvo;
    }
    
}

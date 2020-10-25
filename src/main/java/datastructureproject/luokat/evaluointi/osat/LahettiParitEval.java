package datastructureproject.luokat.evaluointi.osat;

import chess.model.Side;
import datastructureproject.luokat.nappulat.Lahetti;
import datastructureproject.luokat.nappulat.Nappula;

public class LahettiParitEval implements OsaEvaluaattori {
    private static final double PAINOARVO = 1d; 
    private Side puoli;

    private int oLahetit;
    private int vLahetit;

    public LahettiParitEval(Side puoli) {
        this.puoli = puoli;

        this.oLahetit = 0;
        this.vLahetit = 0;
    }

    @Override
    public void prosessoiNappula(Nappula nappula) {
        if (nappula.getPuoli() == puoli) {
            if (nappula instanceof Lahetti) {
                oLahetit++;
            }
        } else {
            if (nappula instanceof Lahetti) {
                vLahetit++;
            }
        }
    }

    @Override
    public double getArvo() {
        double arvo = 0;
        
        if (oLahetit == 2) {
            arvo += PAINOARVO;
        }

        if (vLahetit == 2) {
            arvo -= PAINOARVO;
        }

        return arvo;
    }
    
}

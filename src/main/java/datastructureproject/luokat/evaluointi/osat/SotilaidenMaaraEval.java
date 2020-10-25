package datastructureproject.luokat.evaluointi.osat;

import chess.model.Side;
import datastructureproject.luokat.nappulat.Nappula;
import datastructureproject.luokat.nappulat.Sotilas;

public class SotilaidenMaaraEval implements OsaEvaluaattori {
    private static final double PAINOARVO = 2d;
    private Side puoli;
    private int oSotilaat;
    private int vSotilaat;

    public SotilaidenMaaraEval(Side puoli) {
        this.puoli = puoli;
        this.oSotilaat = 0;
        this.vSotilaat = 0;
    }


    @Override
    public void prosessoiNappula(Nappula nappula) {
        if (nappula instanceof Sotilas) {
            if (nappula.getPuoli() == puoli) {
                oSotilaat++;
            } else {
                vSotilaat++;
            }
        }
    }

    @Override
    public double getArvo() {
        double arvo = 0;
        if (oSotilaat == 0) {
            arvo -= PAINOARVO;
        }
        if (vSotilaat == 0) {
            arvo += PAINOARVO;
        }
        return arvo;
    }
    
}

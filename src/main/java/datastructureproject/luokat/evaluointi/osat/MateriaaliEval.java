package datastructureproject.luokat.evaluointi.osat;

import chess.model.Side;
import datastructureproject.luokat.ShakkiAly;
import datastructureproject.luokat.nappulat.*;

public class MateriaaliEval implements OsaEvaluaattori {
    private Side puoli;
    private double arvo;

    public MateriaaliEval(Side puoli) {
        this.puoli = puoli;
        this.arvo = 0;
    }

    public double materiaaliArvo(Nappula n) {
        if (n instanceof Kuningas) {
            return ShakkiAly.MAX;
        } else if (n instanceof Kuningatar) {
            return 18d;
        } else if (n instanceof Torni) {
            return 10d;
        } else if (n instanceof Lahetti || n instanceof Ratsu) {
            return 6d;
        } else if (n instanceof Sotilas) {
            return 2d;
        }
        
        return 0d;
    }
    
    @Override
    public void prosessoiNappula(Nappula n) {
        arvo += (n.getPuoli() == puoli ? +1d : -1d) * materiaaliArvo(n);
    }

    @Override
    public double getArvo() {
        return arvo;
    }
    
}

package datastructureproject.luokat.evaluointi;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.nappulat.Nappula;
import datastructureproject.luokat.evaluointi.osat.*;

public class OmaEvaluaattori implements Evaluaattori {
    public double getLaudanArvo(Pelilauta lauta, Side vuoro) {
        OsaEvaluaattori[] evaluaattorit = new OsaEvaluaattori[]{
            new MateriaaliEval(vuoro), 
            new SotilasRivitEval(vuoro),
            new SotilasEstettyEval(lauta, vuoro),
            new LahettiParitEval(vuoro),
            new RuutuPainotEval(vuoro),
        };

        for (int y = 0; y < lauta.getKoko(); y++) {
            for (int x = 0; x < lauta.getKoko(); x++) {
                Nappula nappula = lauta.lauta[y][x];
                if (nappula == null) {
                    continue;
                }

                for (OsaEvaluaattori eval: evaluaattorit) {
                    eval.prosessoiNappula(nappula);
                }
            }
        }
        
        double arvo = 0;

        for (OsaEvaluaattori eval: evaluaattorit) {
            arvo += eval.getArvo();
        }

        return arvo;
    }
}

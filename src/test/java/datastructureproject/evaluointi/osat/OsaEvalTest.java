package datastructureproject.evaluointi.osat;

import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.evaluointi.osat.OsaEvaluaattori;
import datastructureproject.luokat.nappulat.Nappula;

public abstract class OsaEvalTest {
    public void prosessoiNappulat(Pelilauta lauta, OsaEvaluaattori eval) {
        for (int y = 0; y < lauta.getKoko(); y++) {
            for (int x = 0; x < lauta.getKoko(); x++) {
                Nappula nappula = lauta.getNappula(x, y);
                if (nappula != null) {
                    eval.prosessoiNappula(nappula);
                }
            }
        }
    }

}

package datastructureproject.luokat.evaluointi.osat;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;
import datastructureproject.luokat.nappulat.Nappula;
import datastructureproject.luokat.nappulat.Sotilas;
import datastructureproject.luokat.tietorakenteet.Lista;

public class SotilasRivitEval implements OsaEvaluaattori {
    private static final double PAINOARVO = 0.5d; 
    private Side puoli;

    private Lista<Sotilas> oSotilaat;
    private Lista<Sotilas> vSotilaat;
    private int[] oSotilasRivit;
    private int[] vSotilasRivit;

    public SotilasRivitEval(Side puoli) {
        oSotilaat = new Lista<>();
        vSotilaat = new Lista<>();
        oSotilasRivit = new int[Pelilauta.ALKUTILANNE.length];
        vSotilasRivit = new int[Pelilauta.ALKUTILANNE.length];
        this.puoli = puoli;
    }

    public void prosessoiNappula(Nappula n) {
        if (n instanceof Sotilas) {
            if (n.getPuoli() == puoli) {
                oSotilasRivit[n.getX()]++;
                oSotilaat.add((Sotilas) n);
            } else {
                vSotilasRivit[n.getX()]++;
                vSotilaat.add((Sotilas) n);
            }
        }
    }

    private double getArvo(int[] rivit, Lista<Sotilas> sotilaat) {
        double d = 0d;

        for (int x = 0; x < rivit.length; x++) {
            //Tuplattu sotilas - Katsotaan onko samalla rivillä useampi saman puolen sotilas
            if (rivit[x] >= 2) {
                d -= rivit[x] / 2d * PAINOARVO; // tuplattu: -0.5, triplattu: -0.75, quadruplattu: -1
            }
        }

        for (int i = 0; i < sotilaat.size(); i++) {
            Sotilas sotilas = sotilaat.get(i);
            int x = sotilas.getX();
            
            //Yksinäinen sotilas - Katsotaan onko viereisillä riveillä sotilaalle kavereita
            int kaveriAste = 0;
            if (x > 0 && rivit[x - 1] > 0) {
                kaveriAste++;
            }
            if (x < oSotilasRivit.length - 1 && rivit[x + 1] > 0) {
                kaveriAste++;
            }

            //Jos sotilaalla ei ole kavereita, pienennetään arvoa kertoimen verran
            if (kaveriAste == 0) {
                d -= PAINOARVO;
            }
        }

        return d;
    }

    public double getArvo() {
        return getArvo(oSotilasRivit, oSotilaat) - getArvo(vSotilasRivit, vSotilaat);
    }
}

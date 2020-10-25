package datastructureproject.luokat.evaluointi;

import chess.model.Side;
import datastructureproject.luokat.Pelilauta;

public interface Evaluaattori {

    /**
     * Heuristiikka jolla arvioidaan pelitilanteen 'arvoa' jommankumman puolen pelaajan kannalta
     * 
     * @param lauta pelilaudan tilanne, jota tarkastellaan
     * @param puoli kumman puolen pelaajan kannalta tilannetta tarkastellaan
     * @return laudan arvo. Jos arvo isompi kuin 0, niin tilanne on tämän puolen pelaajan kannalta hyvä
     */
    double getLaudanArvo(Pelilauta lauta, Side puoli);
}

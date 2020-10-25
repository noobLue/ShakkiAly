package datastructureproject.luokat.evaluointi.osat;

import datastructureproject.luokat.nappulat.Nappula;

public interface OsaEvaluaattori {
    /**
     * Prosessoi nappulan tiedot, joiden perusteella myöhemmin palautetaan arvo.
     * Tämä kutsutaan kaikille laudalla olevilla nappuloille.
     * @param nappula nappula joka prosessoidaan (ei saa olla null)
     */
    void prosessoiNappula(Nappula nappula);

    /**
     * Palauttaa tämän osa-evaluaattorin laskeman laudan arvon.
     * @return laudan arvo
     */
    double getArvo();
}

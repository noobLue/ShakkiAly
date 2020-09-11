package datastructureproject.luokat;

/**
 * Kuvaa shakissa yhden nappulan siirtoa
 */
public class Siirto {
    private static final char EI_YLENNYSTA = '0';
    private char ylennys;
    private Ruutu alku;
    private Ruutu kohde;

    /**
     * 
     * @param alku siiron alkuruutu
     * @param kohde siirron kohderuutu
     */
    public Siirto(Ruutu alku, Ruutu kohde){
        this.alku = alku;
        this.kohde = kohde;
        ylennys = EI_YLENNYSTA;
    }

    /**
     * 
     * @param alku siirron lahtoruutu
     * @param kohde siirron kohderuutu
     * @param ylennys mahdollisen ylennyksen merkki UCI-formaatissa
     */
    public Siirto(Ruutu alku, Ruutu kohde, char ylennys){
        this.alku = alku;
        this.kohde = kohde;
        this.ylennys = ylennys;
    }

    /**
     * 
     * @param siirto UCI-formaatin mukaisesti
     */
    public Siirto(String siirto){
        alku = new Ruutu(siirto.substring(0, 2));
        kohde = new Ruutu(siirto.substring(2, 4));
        ylennys = siirto.length() == 5 ? siirto.charAt(4) : EI_YLENNYSTA;
    }

    /**
     * 
     * @return sisaltaako tama siirto ylennyksen
     */
    public boolean onkoYlennysSiirto(){
        return ylennys != EI_YLENNYSTA;
    }

    public char getYlennys() {
        return ylennys;
    }

    public Ruutu getAlku() {
        return alku;
    }

    public Ruutu getKohde() {
        return kohde;
    }

}

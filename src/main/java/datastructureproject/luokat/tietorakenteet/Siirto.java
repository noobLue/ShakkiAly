package datastructureproject.luokat.tietorakenteet;

/**
 * Kuvaa shakissa yhden nappulan siirtoa
 */
public class Siirto {
    private static final char EI_YLENNYSTA = '0';
    /**
     * Sotilaan ylennys.
     */
    private char ylennys;
    private Ruutu alku;
    private Ruutu kohde;

    /**
     * 
     * @param alku siiron alkuruutu
     * @param kohde siirron kohderuutu
     */
    public Siirto(Ruutu alku, Ruutu kohde) {
        this.alku = alku.kopioi();
        this.kohde = kohde.kopioi();
        this.ylennys = EI_YLENNYSTA;
    }

    /**
     * 
     * @param x ensimmaisen ruudun x koordinaatti
     * @param y ensimmaisen ruudun y koordinaatti
     * @param x2 toisen ruudun x koordinaatti
     * @param y2 toisen ruudun y koordinaatti
     */
    public Siirto(int x, int y, int x2, int y2) {
        this.alku = new Ruutu(x, y);
        this.kohde = new Ruutu(x2, y2);
        this.ylennys = EI_YLENNYSTA;
    }
    
    /**
     * 
     * @param x ensimmaisen ruudun x koordinaatti
     * @param y ensimmaisen ruudun y koordinaatti
     * @param x2 toisen ruudun x koordinaatti
     * @param y2 toisen ruudun y koordinaatti
     * @param ylennys sotilaan ylennys merkki
     */
    public Siirto(int x, int y, int x2, int y2, char ylennys) {
        this.alku = new Ruutu(x, y);
        this.kohde = new Ruutu(x2, y2);
        this.ylennys = ylennys;
    }

    /**
     * 
     * @param alku siirron lahtoruutu
     * @param kohde siirron kohderuutu
     * @param ylennys mahdollisen ylennyksen merkki UCI-formaatissa
     */
    public Siirto(Ruutu alku, Ruutu kohde, char ylennys) {
        this.alku = alku.kopioi();
        this.kohde = kohde.kopioi();
        this.ylennys = ylennys;
    }

    /**
     * 
     * @param siirto UCI-formaatin mukaisesti
     */
    public Siirto(String siirto) {
        alku = new Ruutu(siirto.substring(0, 2));
        kohde = new Ruutu(siirto.substring(2, 4));
        ylennys = siirto.length() == 5 ? siirto.charAt(4) : EI_YLENNYSTA;
    }

    public String getUCIString() {
        return alku.getAsString() + kohde.getAsString() + (this.ylennys != EI_YLENNYSTA ? ylennys : "");
    }

    /**
     * 
     * @return sisaltaako tama siirto ylennyksen
     */
    public boolean onYlennys() {
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

    @Override
    public String toString() {
        return getUCIString();
    }

    
}

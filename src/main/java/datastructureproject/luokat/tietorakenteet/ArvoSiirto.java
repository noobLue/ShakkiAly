package datastructureproject.luokat.tietorakenteet;

/** 
 * Wrapperi siirrolle ja sen haaran saamalle arvolle.
 */
public class ArvoSiirto {
    private int arvo;
    private Siirto siirto;

    public ArvoSiirto(int arvo, Siirto siirto) {
        this.arvo = arvo;
        this.siirto = siirto;
    }

    public int getArvo() {
        return arvo;
    }

    public void setArvo(int arvo) {
        this.arvo = arvo;
    }

    public Siirto getSiirto() {
        return siirto;
    }

    public void setSiirto(Siirto siirto) {
        this.siirto = siirto;
    }

    /**
     * Asettaa sekä arvon että siirron
     * @param arvo siirron arvo
     * @param siirto siirto
     */
    public void set(int arvo, Siirto siirto) {
        this.arvo = arvo;
        this.siirto = siirto;
    }
}

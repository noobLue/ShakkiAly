package datastructureproject.luokat.tietorakenteet;

/** 
 * Kahden tyyppiä T ja E olevien olioiden sisältävä olio
 */
public class Tupla<T, E> {
    private T t;
    private E e;

    public Tupla(T t, E e) {
        this.t = t;
        this.e = e;
    }

    public T getEka() {
        return t;
    }

    public void setEka(T arvo) {
        this.t = arvo;
    }

    public E getToka() {
        return e;
    }

    public void setToka(E siirto) {
        this.e = siirto;
    }

    /**
     * Asettaa molemmat arvot
     * @param t ensimmäinen objekti
     * @param e toinen objekti
     */
    public void set(T t, E e) {
        this.t = t;
        this.e = e;
    }
}

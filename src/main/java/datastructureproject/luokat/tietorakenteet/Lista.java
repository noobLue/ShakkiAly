package datastructureproject.luokat.tietorakenteet;

/**
 * Siirtoja sisältävä lista
 */
public class Lista<T> {
    private T[] siirrot;
    private int seuraavaIndeksi;

    @SuppressWarnings("unchecked")
    public Lista() {
        siirrot = (T[]) new Object[10];
        seuraavaIndeksi = 0;
    }

    public int size() {
        return seuraavaIndeksi;
    }

    public T get(int index) {
        return siirrot[index];
    }

    public T getVika() {
        if (isEmpty()) {
            return null;
        }
        return siirrot[seuraavaIndeksi - 1];
    }

    /**
     * Palauttaa viimeksi laitetun olion ja 'poistaa' sen listasta (pienentämällä indeksimittaria).
     * 
     * @return Listan viimeisin olio
     */
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        //Vähän hassusti kirjoitettu, mutta toimii kyllä:
        seuraavaIndeksi--;
        return siirrot[seuraavaIndeksi]; 
    }

    public boolean isEmpty() {
        return seuraavaIndeksi == 0;
    }

    /**
     * Siirron lisääminen listaan.
     * @param siirto lisättävä siirto
     */
    public void add(T siirto) {
        // Tarvittaessa suurennetaan taulukkoa
        if (seuraavaIndeksi >= siirrot.length) {
            lisaaTilaa(siirrot.length);
        }

        siirrot[seuraavaIndeksi++] = siirto;
    }

    /**
     * Lisätään toisen listan arvot tähän listaan. 
     * @param lista lista, jonka arvot halutaan lisätä tähän listaan
     */
    public void addAll(Lista<T> lista) {
        // Lisätään tarvittaessa tilaa
        if (seuraavaIndeksi + lista.size() >= siirrot.length) {
            // Ennakoidaan hieman ja lisätään vielä enemmän tilaa kun tarvitaan
            lisaaTilaa(lista.size() + siirrot.length / 2);
        }

        for (int i = 0; i < lista.size(); i++) {
            add(lista.get(i));
        }
    }

    /**
     * Suurennetaan taulukon kokoa parametrina annettun luvun verran.
     * @param koko Kuinka paljon tilaa halutaan lisätä
     */
    @SuppressWarnings("unchecked")
    private void lisaaTilaa(int koko) {
        T[] uusiSiirrot = (T[]) new Object[siirrot.length + koko];
        for (int i = 0; i < seuraavaIndeksi; i++) {
            uusiSiirrot[i] = siirrot[i];
        }
        siirrot = uusiSiirrot;
    }
}

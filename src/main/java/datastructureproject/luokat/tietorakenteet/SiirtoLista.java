package datastructureproject.luokat.tietorakenteet;

/**
 * Siirtoja sisältävä lista
 */
public class SiirtoLista {
    private Siirto[] siirrot;
    private int indeksi;


    public SiirtoLista() {
        siirrot = new Siirto[10];
        indeksi = 0;
    }

    public int size() {
        return indeksi;
    }

    public Siirto get(int index) {
        return siirrot[index];
    }

    public boolean isEmpty() {
        return indeksi == 0;
    }

    public void add(Siirto siirto) {
        if (indeksi >= siirrot.length) {
            Siirto[] uusiSiirrot = new Siirto[siirrot.length * 2];
            for (int i = 0; i < indeksi; i++) {
                uusiSiirrot[i] = siirrot[i];
            }
            siirrot = uusiSiirrot;
        }

        siirrot[indeksi++] = siirto;
    }

    public void addAll(SiirtoLista lista) {
        for (int i = 0; i < lista.size(); i++) {
            add(lista.get(i));
        }
    }
}

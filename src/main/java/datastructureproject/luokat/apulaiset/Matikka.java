package datastructureproject.luokat.apulaiset;

public class Matikka {
    /**
     * Palauttaa parametreista pienimmän arvon. 
     * Jos molemman arvot ovat samoja, myös sama arvo palautetaan.
     * 
     * @param a ensimmäinen numero
     * @param b toinen numero
     * @return pienemmän arvon muuttujista a ja b
     */
    public static int minimi(int a, int b) {
        return a <= b ? a : b;
    }

    /**
     * Palauttaa parametreista pienimmän arvon. 
     * Jos molemman arvot ovat samoja, myös sama arvo palautetaan.
     * 
     * @param a ensimmäinen numero
     * @param b toinen numero
     * @return pienemmän arvon muuttujista a ja b
     */
    public static double minimi(double a, double b) {
        return a <= b ? a : b;
    }

    /**
     * Palauttaa parametreista suurimman arvon.
     * Jos molemman arvot ovat samoja, palautetaan ensimmäinen parametri.
     * 
     * @param a ensimmäinen int-tyyppinen numero
     * @param b toinen int-tyyppinen numero
     * @return suurin arvon muuttujista a ja b
     */
    public static int maksimi(int a, int b) {
        return a >= b ? a : b;
    }

    /**
     * Palauttaa parametreista suurimman arvon.
     * Jos molemman arvot ovat samoja, palautetaan ensimmäinen parametri.
     * 
     * @param a ensimmäinen double-tyyppinen numero
     * @param b toinen double-tyyppinen numero
     * @return suurin arvon muuttujista a ja b
     */
    public static double maksimi(double a, double b) {
        return a >= b ? a : b;
    }

    /**
     * Laskee luvun itseisarvon. 
     * Jos parametrina annettu luku on positiivnen (nolla mukaanlukien), palautetaan kyseinen arvo. 
     * Jos taas luku on negatiivinen, palautetaan sen vastaluku.
     * 
     * @param a luku
     * @return parametrin a itseisarvo
     */
    public static int itseisarvo(int a) {
        return a >= 0 ? a : -a;
    }
}

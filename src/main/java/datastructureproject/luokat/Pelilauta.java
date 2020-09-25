package datastructureproject.luokat;

import java.util.ArrayList;

import chess.model.Side;
import datastructureproject.luokat.nappulat.Kuningas;
import datastructureproject.luokat.nappulat.Kuningatar;
import datastructureproject.luokat.nappulat.Lahetti;
import datastructureproject.luokat.nappulat.Nappula;
import datastructureproject.luokat.nappulat.Ratsu;
import datastructureproject.luokat.nappulat.Sotilas;
import datastructureproject.luokat.nappulat.Torni;

public class Pelilauta {
    private static int koko = 8;
    public int getKoko() {
        return koko;
    }

    /**
     * Kuvaa shakkilaudan tilannetta
     */
    public Nappula[][] lauta;
    public Siirto viimeSiirto = null;

    public Pelilauta(ShakkiTemplaatti templaatti) {
        lauta = new Nappula[templaatti.getTemplaatti().length][templaatti.getTemplaatti()[0].length];
        alusta(templaatti);
    }

    /**
     * Tekee syväkopion aiemmasta pelilaudan tilanteesta.
     * 
     * @param edellinen edellinen laudan tilanne
     */
    public Pelilauta(Pelilauta edellinen) {
        lauta = new Nappula[edellinen.lauta.length][edellinen.lauta[0].length];
        viimeSiirto = edellinen.viimeSiirto;
        for (int y = 0; y < edellinen.lauta.length; y++) {
            for (int x = 0; x < edellinen.lauta[y].length; x++) {
                if (edellinen.lauta[y][x] == null) {
                    lauta[y][x] = null;
                }
                else {
                    lauta[y][x] = edellinen.lauta[y][x].kopioi();
                }
            }
        }
    }

    /**
     * Tekee siirron
     * 
     * @param siirto
     */
    private void siirto(Siirto siirto) {
        Nappula siirrettava = getNappula(siirto.getAlku());
        nollaaRuutu(siirto.getAlku());

        //Erikoissiirrot:
        //Tornitus
        if (siirrettava instanceof Kuningas && Math.abs(siirto.getAlku().getX() - siirto.getKohde().getX()) > 1) {
            boolean kohdeIsompi  = (siirto.getKohde().getX() - siirto.getAlku().getX() > 0);
            int torniX = kohdeIsompi ? getKoko() - 1 : 0;
            int torniUusiX = 1 + (kohdeIsompi ? siirto.getAlku().getX() : siirto.getKohde().getX());

            Nappula torni = lauta[siirto.getAlku().getY()][torniX];
            if (!(torni instanceof Torni)) {
                throw new Error("Joku yritti tornittaa ilman tornia");
            }
                
            // Tornituksen sääntöjen mukaan torni ei saa uudessa ruudussa tulla uhatuksi 
            // (koska kuningas olisi tavallisesti liikkunut siihen ja shakin erikoisliikkeiden erikoissäännöt ovat hyvin erikoisia)
            // Oletetaan kuitenkin että vihollinen tekee vain sallittuja liikkeitä, eikä itse tehdä tornituksia
            // Jos tornitukset sisälletään omaan liikehdintään, tulee silloin tarkastaa ettei torni tule uhatuksi


            //Siirretään torni 
            lauta[siirto.getAlku().getY()][torniX] = null;
            torni.setRuutu(torniUusiX, siirto.getAlku().getY());
            lauta[siirto.getAlku().getY()][torniUusiX] = torni;
        }

        //Prosessoi En passant - liike, olettaen ettei vastustaja tee laittomia liikkeitä
        if(Math.abs(siirto.getAlku().getY() - siirto.getKohde().getY()) >= 1
                && Math.abs(siirto.getAlku().getX() - siirto.getKohde().getX()) >= 1
                && siirrettava instanceof Sotilas
                && getNappula(siirto.getKohde()) == null){
            int uusiY = siirto.getKohde().getEteenpainY(siirrettava.getPuoli(), -1);
            lauta[uusiY][siirto.getKohde().getX()] = null;
        }

        //Prosessoi ylennys
        if (siirrettava instanceof Sotilas
            && siirto.onkoYlennysSiirto()) {

            Side puoli = siirrettava.getPuoli();
            Ruutu ruutu = siirto.getAlku();
            if (siirto.getYlennys() == 'q') {
                siirrettava = new Kuningatar(puoli, ruutu);
            } else if (siirto.getYlennys() == 'n') {
                siirrettava = new Ratsu(puoli, ruutu);
            } else if (siirto.getYlennys() == 'b') {
                siirrettava = new Lahetti(puoli, ruutu);
            } else if (siirto.getYlennys() == 'r') {
                siirrettava = new Torni(puoli, ruutu);
            } else {
                throw new Error("Joku yritti ylentää sotilaan sallimattomaksi nappulaksi (" + siirto.getYlennys() + ")");
            }
            lauta[siirto.getAlku().getY()][siirto.getAlku().getX()] = siirrettava;
        }

        //Sijoittaa nappulan laudalle ja päivittää nappulan oman sijainnin tiedon
        sijoitaNappula(siirrettava, siirto.getKohde());
        //Talletetaan viime siirto muistiin
        viimeSiirto = siirto; 
    }

    /**
     * Palauttaa pelaajan Kuningas-nappulan
     * 
     * @param puoli kumman pelaajan kuningas etsitään
     * @return tämän pelaajan kuningas
     */
    public Kuningas etsiKuningas(Side puoli) {
        for (int y = 0; y < lauta.length; y++) {
            for (int x = 0; x < lauta[y].length; x++) {
                Nappula n = lauta[y][x];
                if (n instanceof Kuningas && n.getPuoli() == puoli) {
                    return (Kuningas) n;
                }
            }
        }
        return null;
    }

    /**
     * Toteuttaa syötteessä annetun siirron pelilaudalle
     * 
     * @param siirto Siirto joka halutaan tehdä
     * @return Uusi pelilaudan tilanne siirron jälkeen
     */
    public Pelilauta toteutaSiirto(Siirto siirto) {
        Pelilauta lautaUusi = new Pelilauta(this);
        lautaUusi.siirto(siirto);
        return lautaUusi;
    }

    /**
     * 
     * @param x koordinaatti laudalla
     * @param y koordinaatti laudalla
     * @return koordinaateissa olevan nappulan
     */
    public Nappula getNappula(int x, int y) {
        return lauta[y][x];
    }

    public Nappula getNappula(Ruutu ruutu) {
        return lauta[ruutu.getY()][ruutu.getX()];
    }

    public void nollaaRuutu(Ruutu ruutu) {
        lauta[ruutu.getY()][ruutu.getX()] = null;
    }

    /**
     * 
     * @param nappula Nappula, joka sijoitetaan (takaisin pelilaudalle)
     * @param ruutu Ruutu johon nappula laitetaan
     */
    private void sijoitaNappula(Nappula nappula, Ruutu ruutu) {
        lauta[ruutu.getY()][ruutu.getX()] = nappula;
        if (nappula != null) {
            nappula.setRuutu(ruutu.getX(), ruutu.getY());
        }
    }

    /**
     * Hakee tämän pelaajan mahdolliset liikkeet pelilaudalla.
     * 
     * @param puoli kumman pelaajan vuoro on. 
     * @return listan siirroista jotka pelaaja voi tehdä. 
     */
    public ArrayList<Siirto> kaikkiLiikeet(Side puoli) {
        ArrayList<Siirto> siirrot = new ArrayList<>();
        
        for (int y = 0; y < getKoko(); y++) {
            for (int x = 0; x < getKoko(); x++) {
                Nappula n = lauta[y][x];
                if (n != null && n.getPuoli() == puoli) {
                    siirrot.addAll(n.kaikkiSiirrot(this));
                }
            }
        }

        return siirrot;
    } 

    /**
     * Printtaa pelilaudan tilanteen, debug-toiminta
     */
    public void printtaa() {
        for (int y = lauta.length - 1; y >= 0; y--) {
            for (int x = 0; x < lauta[y].length; x++) {
                if (lauta[y][x] == null) {
                    System.out.print("?, ");
                } else {
                    System.out.print((lauta[y][x].getPuoli() == Side.BLACK ? lauta[y][x].toString().toUpperCase() : lauta[y][x]) + ", ");
                }
            }
            System.out.println("");
        }
    }

    /**
     * Alustaa pelilaudan templaatin mukaiseen alkuasentoon
     */
    private void alusta(ShakkiTemplaatti templaatti) {
        for (int y = 0; y < templaatti.getTemplaatti().length; y++) {
            for (int x = 0; x < templaatti.getTemplaatti()[0].length; x++) {
                switch (templaatti.getTemplaatti()[y][x]) {
                    case 'r':
                        lauta[y][x] = new Torni(templaatti.kummanRivi(y), new Ruutu(x, y));
                        break;
                    case 'n':
                        lauta[y][x] = new Ratsu(templaatti.kummanRivi(y), new Ruutu(x, y));
                        break;
                    case 'b':
                        lauta[y][x] = new Lahetti(templaatti.kummanRivi(y), new Ruutu(x, y));
                        break;
                    case 'q':
                        lauta[y][x] = new Kuningatar(templaatti.kummanRivi(y), new Ruutu(x, y));
                        break;
                    case 'k':
                        lauta[y][x] = new Kuningas(templaatti.kummanRivi(y), new Ruutu(x, y));
                        break;
                    case 'p':
                        lauta[y][x] = new Sotilas(templaatti.kummanRivi(y), new Ruutu(x, y));
                        break;
                    default:
                        break;
                }

            }
        }
    }
}

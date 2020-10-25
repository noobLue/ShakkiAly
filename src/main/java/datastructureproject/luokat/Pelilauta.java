package datastructureproject.luokat;

import chess.model.Side;
import datastructureproject.luokat.apulaiset.Matikka;
import datastructureproject.luokat.nappulat.Kuningas;
import datastructureproject.luokat.nappulat.Kuningatar;
import datastructureproject.luokat.nappulat.Lahetti;
import datastructureproject.luokat.nappulat.Nappula;
import datastructureproject.luokat.nappulat.Ratsu;
import datastructureproject.luokat.nappulat.Sotilas;
import datastructureproject.luokat.nappulat.Torni;
import datastructureproject.luokat.tietorakenteet.*;

public class Pelilauta {
    /** 
     * Laudan alkutilan esitys. Versaalit kuvaavat mustan pelaajan nappuloita.
     * r = torni, n = ratsu, b = lähetti, q = kuningatar, k = kuningas, p = sotilas
     */
    public static final char[][] ALKUTILANNE = new char[][] {
        {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' }, 
        {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, 
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
        {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' }, 
        {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' }
    };

    /**
     * Kuvaa shakkilaudan tilannetta
     */
    public final Nappula[][] lauta;
    private Ruutu valkoinenKuningas;
    private Ruutu mustaKuningas;


    private Lista<Lista<Tupla<Ruutu, Nappula>>> historia = new Lista<>();

    /**
     * Luo uuden pelilaudan shakin aloitustilanteelle
     */
    public Pelilauta() {
        lauta = new Nappula[ALKUTILANNE.length][ALKUTILANNE[0].length];
        alusta(ALKUTILANNE);
    }

    /**
     * Luo uuden pelilaudan testitilanteen tarkastelua varten
     * @param templaatti pelin alkutilanne tai keskeneräisen pelin tilanne
     */
    public Pelilauta(char[][] templaatti) {
        lauta = new Nappula[templaatti.length][templaatti[0].length];
        alusta(templaatti);
    }

    private void talletaKuningasMuistiin(Nappula n, Ruutu kohde) {
        //Tallennetaan kuninkaan sijainti muistiin
        if (n instanceof Kuningas) {
            if (n.getPuoli() == Side.WHITE) { 
                valkoinenKuningas = kohde.kopioi(); 
            } else {
                mustaKuningas = kohde.kopioi(); 
            }
        }
    }

    /**
     * Toteuttaa siirron tällä laudalla
     * 
     * @param siirto
     */
    public void siirto(Siirto siirto) {
        Nappula siirrettava = getNappula(siirto.getAlku());
        Lista<Tupla<Ruutu, Nappula>> historiaSiirrot = new Lista<>();

        //talleta liikkeen alkuruutu
        historiaSiirrot.add(new Tupla<>(siirto.getAlku().kopioi(), siirrettava.kopioi()));
        //talleta liikkeen kohderuutu
        if (getNappula(siirto.getKohde()) != null) {
            historiaSiirrot.add(new Tupla<>(siirto.getKohde().kopioi(), getNappula(siirto.getKohde()).kopioi()));
        } else {
            historiaSiirrot.add(new Tupla<>(siirto.getKohde().kopioi(), (Nappula) null));
        }

        nollaaRuutu(siirto.getAlku());

        //Tallennetaan kuninkaan sijainti muistiin
        if (siirrettava instanceof Kuningas) {
            talletaKuningasMuistiin(siirrettava, siirto.getKohde());
        }

        //Erikoissiirrot:
        //https://fi.wikipedia.org/wiki/Tornitus
        //Tornitus
        int siirronPituusX = Matikka.itseisarvo(siirto.getAlku().getX() - siirto.getKohde().getX());
        int siirronPituusY = Matikka.itseisarvo(siirto.getAlku().getY() - siirto.getKohde().getY());

        if (siirrettava instanceof Kuningas && siirronPituusX > 1) {
            boolean kohdeIsompi  = (siirto.getKohde().getX() - siirto.getAlku().getX() > 0);
            int torniX = kohdeIsompi ? getKoko() - 1 : 0;
            int torniUusiX = 1 + (kohdeIsompi ? siirto.getAlku().getX() : siirto.getKohde().getX());

            Nappula torni = lauta[siirto.getAlku().getY()][torniX];
            if (!(torni instanceof Torni)) {
                throw new Error("Joku yritti tornittaa ilman tornia");
            }
                
            // Tornituksen sääntöjen mukaan torni ei saa uudessa ruudussa tulla uhatuksi 
            // (koska kuningas olisi tavallisesti liikkunut siihen 
            // ja shakin erikoisliikkeiden erikoissäännöt ovat hyvin erikoisia)
            // Oletetaan kuitenkin että vihollinen tekee vain sallittuja 
            // liikkeitä, eikä itse tehdä tornituksia
            // Jos tornitukset sisälletään omaan liikehdintään, tulee silloin tarkastaa ettei torni tule uhatuksi

            //Talletetaan tornin alkuruutu historiaan
            historiaSiirrot.add(new Tupla<>(torni.getRuutu().kopioi(), torni.kopioi()));

            //Siirretään torni 
            lauta[siirto.getAlku().getY()][torniX] = null;
            torni.setRuutu(torniUusiX, siirto.getAlku().getY());
            lauta[siirto.getAlku().getY()][torniUusiX] = torni;

            //Talletetaan tornin kohderuutu historiaan
            historiaSiirrot.add(new Tupla<>(torni.getRuutu().kopioi(), (Nappula) null));
        }

        //https://fi.wikipedia.org/wiki/Ohestaly%C3%B6nti
        //Prosessoi Ohestalyönti liike, olettaen ettei vastustaja tee laittomia liikkeitä
        if (siirronPituusY >= 1
                && siirronPituusX >= 1
                && siirrettava instanceof Sotilas
                && getNappula(siirto.getKohde()) == null) {
            int uusiY = siirto.getKohde().getEteenpainY(siirrettava.getPuoli(), -1);
            Ruutu ohestaLyoty = new Ruutu(siirto.getKohde().getX(), uusiY);

            //Talletetaan ohestalyöty nappula historiaan
            historiaSiirrot.add(new Tupla<>(ohestaLyoty.kopioi(), getNappula(ohestaLyoty).kopioi()));

            lauta[ohestaLyoty.getY()][ohestaLyoty.getX()] = null;
        }

        
        //https://fi.wikipedia.org/wiki/Sotilas_(shakki)
        //Prosessoi sotilaan ylennys
        if (siirrettava instanceof Sotilas && siirto.onYlennys()) {

            Side puoli = siirrettava.getPuoli();
            Ruutu ruutu = siirto.getKohde().kopioi();
            if (siirto.getYlennys() == 'q') {
                siirrettava = new Kuningatar(puoli, ruutu);
            } else if (siirto.getYlennys() == 'n') {
                siirrettava = new Ratsu(puoli, ruutu);
            } else if (siirto.getYlennys() == 'b') {
                siirrettava = new Lahetti(puoli, ruutu);
            } else if (siirto.getYlennys() == 'r') {
                siirrettava = new Torni(puoli, ruutu);
            } else {
                throw new Error("Joku yritti ylentää sotilaan sallimattomaksi nappulaksi (" 
                    + siirto.getYlennys() + ")");
            }
            
        } 

        //Sijoittaa nappulan laudalle ja päivittää nappulan oman sijainnin tiedon
        sijoitaNappula(siirrettava, siirto.getKohde().kopioi());

        //Lisätään tämän siirron vaikutukset historialistaan
        historia.add(historiaSiirrot);
    }

    /**
     * Peruutetaan viimeksi toteutettu siirto (toimii vastakohtana siirto(Siirto s) funktiolle)
     */
    public void peruutaSiirto() {
        Lista<Tupla<Ruutu, Nappula>> historiaLista = historia.pop();

        for (int i = 0; i < historiaLista.size(); i++) {
            Tupla<Ruutu, Nappula> hs = historiaLista.get(i);

            Ruutu r = hs.getEka();
            Nappula n = hs.getToka();
            if (n != null) {
                n.setRuutu(r);
            }
            lauta[r.getY()][r.getX()] = n;

            if (n instanceof Kuningas) {
                talletaKuningasMuistiin(n, r);
            }
        }
    }

    /**
     * Palauttaa pelaajan Kuningas-nappulan
     * 
     * @param puoli kumman pelaajan kuningas etsitään
     * @return tämän pelaajan kuningas
     */
    public Kuningas etsiKuningas(Side puoli) {
        Nappula kuningas = puoli == Side.WHITE ? getNappula(valkoinenKuningas) : getNappula(mustaKuningas);
        return (kuningas instanceof Kuningas) ? (Kuningas) kuningas : null;
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

    /**
     * 
     * @param ruutu ruutu jossa oleva nappula halutaan loytaa
     * @return nappulan joka on ruudussa
     */
    public Nappula getNappula(Ruutu ruutu) {
        return lauta[ruutu.getY()][ruutu.getX()];
    }

    /**
     * Poistaa ruudussa olevan nappulan
     * @param ruutu ruutu, jossa oleva nappula halutaan poistaa
     */
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
     * Generoi tietyn puolen pelaajan kaikkien nappuloiden liikkeet.
     * 
     * @param puoli kumman pelaajan vuoro on. 
     * @return listan siirroista jotka pelaaja voi tehdä. 
     */
    public Lista<Siirto> generoiSiirrot(Side puoli) {
        Lista<Siirto> siirrot = new Lista<>();
        
        for (int y = 0; y < getKoko(); y++) {
            for (int x = 0; x < getKoko(); x++) {
                Nappula n = lauta[y][x];
                if (n != null && n.getPuoli() == puoli) {
                    Lista<Siirto> tSiirrot = n.generoiSiirrot(this);
                    siirrot.addAll(tSiirrot);
                }
            }
        }

        return siirrot;
    } 

    /**
     * Alustaa pelilaudan templaatin mukaiseen alkuasentoon
     */
    private void alusta(char[][] templaatti) {
        for (int y = 0; y < templaatti.length; y++) {
            for (int x = 0; x < templaatti[0].length; x++) {
                switch (templaatti[y][x]) {
                    case 'r':
                        lauta[y][x] = new Torni(Side.WHITE, new Ruutu(x, y));
                        break;
                    case 'n':
                        lauta[y][x] = new Ratsu(Side.WHITE, new Ruutu(x, y));
                        break;
                    case 'b':
                        lauta[y][x] = new Lahetti(Side.WHITE, new Ruutu(x, y));
                        break;
                    case 'q':
                        lauta[y][x] = new Kuningatar(Side.WHITE, new Ruutu(x, y));
                        break;
                    case 'k':
                        valkoinenKuningas = new Ruutu(x, y);
                        lauta[y][x] = new Kuningas(Side.WHITE, new Ruutu(x, y));
                        break;
                    case 'p':
                        lauta[y][x] = new Sotilas(Side.WHITE, new Ruutu(x, y));
                        break;

                    case 'R':
                        lauta[y][x] = new Torni(Side.BLACK, new Ruutu(x, y));
                        break;
                    case 'N':
                        lauta[y][x] = new Ratsu(Side.BLACK, new Ruutu(x, y));
                        break;
                    case 'B':
                        lauta[y][x] = new Lahetti(Side.BLACK, new Ruutu(x, y));
                        break;
                    case 'Q':
                        lauta[y][x] = new Kuningatar(Side.BLACK, new Ruutu(x, y));
                        break;
                    case 'K':
                        mustaKuningas = new Ruutu(x, y);
                        lauta[y][x] = new Kuningas(Side.BLACK, new Ruutu(x, y));
                        break;
                    case 'P':
                        lauta[y][x] = new Sotilas(Side.BLACK, new Ruutu(x, y));
                        break;
                    default:
                        break;
                }

            }
        }
    }


    /**
     * Palauttaa laudan leveyden / pituuden
     * @return laudan koko
     */
    public int getKoko() {
        return lauta.length;
    }

    public boolean onShakissa(Side puoli) {
        Kuningas k = etsiKuningas(puoli);
        return k == null || k.olenUhattuna(this);
    }

    public boolean onMatissa(Side vuoro) {
        if (!onShakissa(vuoro)) {
            return false;
        }
        Lista<Siirto> siirrot = generoiSiirrot(vuoro);

        for (int i = 0; i < siirrot.size(); i++) {
            siirto(siirrot.get(i));
            if (!onShakissa(vuoro)) {
                return false;
            }
            peruutaSiirto();
        }

        return true;
    }
}

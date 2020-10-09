
# Viikkoraportti 6

### Mitä olen tehnyt tällä viikolla ja miten on edistynyt

- Pelilauta säilyttää nyt kuninkaiden sijainnit nopeasti haettavassa paikassa. Enää ei tarvitse joka kerta iteroida pelilautaa läpi kun halutaan tietää kuninkaan sijainti. (Hyödyllistä kun karsitaan siirroista pois liikkeet jotka johtavat oman kuninkaan shakitukseen)

- Kokeilin AI:ta lichess botteja vastaan ja hyvin vaikutti toimivan mutta botti piti käynnistää uudelleen jokaisen matsin jälkeen.

- Alustava tehokkuustestaus pelin alkutilanteelle tehty.  

- Koodin siistimistä
    - ShakkiAly-luokassa MiniMaxin supistusta ja selvennystä, 
    - tornin, lähetin ja kuningattaren siirtojen generoinnin tiivistäminen
    - luokkien jakaminen kansioihin
    - Pari pientä bugia fixattu
    - ShakkiTemplaatti luokan poisto ja tilalle char[][] taulukko. Siistimmän näköinen, helpompi käyttää testeissä ja ilmaisuvoimaisempi.
    - Matemaattisille operaatiolle (minimi, maksimi, itseisarvo) oma apu-luokka: Matikka.java

- Dokumentaatiosta muutama virhe pois

- Muutama testi lisää

### Mitä opin?

- Opin shakin pelipuun toiminnasta, esim. keskiverto haarautuvuus pelipuulle on n.31 siirtoa.

### Ongelmia?

MiniMaxin ja Alpha-Beta karsinnan testauksia miettiessä lyö pää vähän tyhjää. Ehkä voisin tehdä yksinkertaisen heuristisen evaluaattorin tyngän (stub), jota käyttäisin testattaessa että MiniMax pääsee oikeaan pelipuun haaraan. 

### Seuraavaksi

Tehokkuustestauksen parantaminen

MiniMaxille ja Alpha-Beta karsinnalle testejen tekoa.

Parempi heuristiikka laudan tilan arviointiin.

Kattavampi käyttöohje?

### Ajankäyttö

Yhteensä: 11 tuntia
# Määrittelydokumentti

Projektin tarkoituksena on luoda shakkiin tekoäly joka käyttää tekniikkana alpha-beta karsintaa. Tekoäly ohjelmoidaan Javalla.

## Algoritmit ja tietorakenteet

Tekoälyn logiikka tulee pyörimaan minmax algoritmilla, lisäyksenä alpha-beta karsinta. Valitsin tämän, sillä se soveltuu hyvin kaksinpeleihin suunnatuille tekoälyille.

Tarvitsen tietorakenteen shakkilaudan tilan säilyttämiseen. Tarvitsen myös tietorakenteet, jotka sisältävät pelinappuloiden tiedot.

## Syötteet ja Tulokset

Projektin tekoäly kommunikoi [projektipohjassa](https://github.com/TiraLabra/chess "TiraLabra/Chess") mukana tulevan rajapinnan kanssa. Tarkoituksena on pystyä pelaaman ottelu shakkia vastapelaajan kanssa loppuun asti. Toivon mukaan saadaan vielä tulostettua joitain statistiikkoja ottelun jälkeen.  

## Aikavaativuus ja tilavaativuus

Huonoimmassa tapauksessa aikavaativuus on **O(b^d)**, parhaassa tapauksessa **O(sqrt(b^d))**, jossa b kuvaa haarautuvuutta (vuoron mahdolliset siirrot) ja d haun syvyyttä. [[1]](##Lähteet)

Tilaa tarvitaan pelitilojen muistissa pitämiseen. Minimaxia suoritettaessa tarvitaan tiettynä hetkenä aina maksimissaan yhden polun verran tilaa, eli shakkilaudan koko kerrottuna polun syvyydellä. Eli O(sd), jossa s on shakkilaudan viemä tila ja d on polun syvyys.

## Koulutusohjelma ja projektin kieli

Tietojenkäsittelytieteen kandidaatti (TKT)

Projekti tehdään kokonaan suomeksi

## Lähteet

[1] https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
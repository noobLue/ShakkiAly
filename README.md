# ShakkiAly
[![CircleCI](https://circleci.com/gh/noobLue/ShakkiAly.svg?style=shield)](https://circleci.com/gh/noobLue/ShakkiAly)

Shakkitekoälyn toteutus Javalla. Tämä projekti on rakennettu ['chess'](https://github.com/TiraLabra/chess/) projektipohjan päälle. Itse tekemäni osuus löytyy pakkauksesta datastructureproject.

## Käyttöohje
.jar tiedoston kääntäminen seuraavalla komennolla.

    ./gradlew build

Tarvittavan tiedoston pitäisi löytyä kansiosta ./build/libs/chess-all.jar

Tätä tekoälyä voi käyttää mm. Winboard/Xboard sovelluksilla ja lichess-shakki serverillä.

Winboardilla esimerkiksi pääsee pelaamaan tekoälyn kanssa käyttämällä seuraavaa configuraatiota: 

    "tira-chess" -fcp "java -jar **HAKEMISTON_SIJAINTI**\ShakkiAly\build\libs\chess-all.jar"

![winboard-setup](./dokumentaatio/winboard-setup.png)


xBoard ja Lichess ohjeet löytyvät englanniksi [projektipohjan dokumentaatiosta](./dokumentaatio/projektipohjan/Beginners_guide.md)

Huom. Lichessissä pelatessa ohjelma täytyy käynnistää jokaisen pelin jälkeen uusiksi.

## Testikattavuus

[Jacoco-testiraportti](https://nooblue.github.io/ShakkiAly/)

## Checkstyle

Projektin buildaamisen jälkeen checkstyle raportti löytyy kansiosta 

`ShakkiAly\build\reports\checkstyle\main.html`

## Dokumentaatio

* [Määrittelydokumentti](./dokumentaatio/maarittelydokumentti.md)

* [Testausdokumentti](./dokumentaatio/testausdokumentti.md)

* [Toteutusdokumentti](./dokumentaatio/toteutusdokumentti.md)


## Viikkoraportit

* [Viikko 1](./dokumentaatio/viikkoraportit/viikko1.md)

* [Viikko 2](./dokumentaatio/viikkoraportit/viikko2.md)

* [Viikko 3](./dokumentaatio/viikkoraportit/viikko3.md)

* [Viikko 4](./dokumentaatio/viikkoraportit/viikko4.md)

* [Viikko 5](./dokumentaatio/viikkoraportit/viikko5.md)

* [Viikko 6](./dokumentaatio/viikkoraportit/viikko6.md)


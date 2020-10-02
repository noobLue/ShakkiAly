
# Toteutusdokumentti

## Syvyysrajoitettu miinmax

Pelilaudan tilannetta ylläpitävä tietorakenne on kaksi ulotteinen taulukko Nappula-luokkaisia olioita. Jokainen Nappula-luokkainen olio osaa palauttaa listan hänen sallituista liikkeistään. Pelilaudan kunkin tilanteen pelipuun saa alkuun hakemalla kaikkien omien nappuloiden sallitut liikket. Sitten aloitetaan minimax algoritmin pyöritys joka arvioi pelipuun tasoja vuorotellen kummankin eri pelaajan kannalta (tekoäly on max-pelaaja, vastustaja on min-pelaaja). Algoritmi olettaa vihollisen pelaavan aina ns. optimaalisti ja valitsee loppujenlopuksi polun joka päätyy optimaalitilanteessa tekoälyn kannalta parhaiten. *Alpha-beta karsintaa* tehdään minimaxin aikana, jonka avulla voidaan ohittaa pelipuun haarat jotka on todettu algoritmin logiikan kannalta turhiksi. Miinmax lopetetaan kun ollaan päästy tiettyyn haluttuun syvyyteen asti ja arvioidaan pelilaudan tilannetta *heurestiikan* avulla.

## Alpha-beta karsinta

Alpha-beta karsinnan avulla voidaan ohittaa minimax:in kannalta turhia pelipuun haaroja. Haarojen ohitus tapahtuu kun huomataan, että haara on **varmasti huonompi**, kuin jokin muu saman tason haara. 

## Heuristiikka

Pelipuun läpikäynti pohjasyvyyteen asti minimaxilla kestäisi aivan liian kauan shakkipelissä, sillä pelipuun koon kasvu on eksponentiaalista luokkaa. Maksimi-syvyyden saavutettua arvioidaan kuinka hyödyllinen pelitilanne on tekoälyn kannalta. 

## Mahdolliset puutteet ja parannusehdotukset

Tekoäly ei osaa tehdä en-passant liikettä (sotilaan erikoisliike) eikä kuninkaan tornitusta. Periaatteessa tilanne jossa en-passant liike olisi ainut validi liike, tekoäly päättää vain luovuttaa. Kuninkaan tornitus on hieman erilainen liike, sillä sitä ei voi tehdä jos kuningas ei voi tehdä yhden askeleen siirtoa samaan suuntaa. Eli tornitus on mahdollinen vain jos kuninkaalla on toinen mahdollinen liike.  

Tekoäly tekee minimaxissa jokaiselle pelinpuun haaralle tarkistuksen, ettei oma kuningas päädy shakitukseen. Tämän tarkastuksen voisi tehdä vasta siinä vaiheessa kun kyseinen haara on katselussa, ja se nopeuttaisi algoritmia koska isoa osaa pelipuun haaroista ei edes katsella tarkemmin.

## Lähteet

https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning

https://materiaalit.github.io/intro-to-ai/part2/

Ohjelman yleisrakenne
Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)
Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen)
Työn mahdolliset puutteet ja parannusehdotukset
Lähteet


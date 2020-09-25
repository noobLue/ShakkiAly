
# Toteutusdokumentti

## Syvyysrajoitettu minmax

Pelilaudan tilannetta ylläpitävä tietorakenne on kaksi ulotteinen taulukko Nappula-luokkaisia olioita. Jokainen Nappula-luokkainen olio osaa palauttaa listan hänen sallituista liikkeistään. Pelilaudan kunkin tilanteen pelipuun saa alkuun hakemalla kaikkien omien nappuloiden sallitut liikket. Sitten aloitetaan minmax algoritmin pyöritys joka arvioi pelipuun tasoja vuorotellen kummankin eri pelaajan kannalta (tekoäly on max-pelaaja, vastustaja on min-pelaaja). Algoritmi olettaa vihollisen pelaavan aina ns. optimaalisti ja valitsee loppujenlopuksi polun joka päätyy optimaalitilanteessa tekoälyn kannalta parhaiten. *Alpha-beta karsintaa* tehdään minmaxin aikana, jonka avulla voidaan ohittaa pelipuun haarat jotka on todettu algoritmin logiikan kannalta turhiksi. Minmax lopetetaan kun ollaan päästy tiettyyn haluttuun syvyyteen asti ja arvioidaan pelilaudan tilannetta *heurestiikan* avulla.

## Alpha-beta karsinta

Alpha-beta karsinnan avulla voidaan ohittaa minmax:in kannalta turhia pelipuun haaroja. Haarojen ohitus tapahtuu kun huomataan, että haara on **varmasti huonompi**, kuin jokin muu saman tason haara. 

## Heurestiikka

Pelipuun läpikäynti pohjasyvyyteen asti minmaxilla kestäisi aivan liian kauan shakkipelissä, sillä pelipuun koon kasvu on exponentiaalista luokkaa. Maksimi-syvyyden saavutettua arvioidaan kuinka hyödyllinen pelitilanne on tekoälyn kannalta. 

## Mahdolliset puutteet ja parannusehdotukset

Tekoäly ei osaa tehdä en-passant liikettä (sotilaan erikoisliike). Periaatteessa tilanne jossa en-passant liike olisi ainut validi liike, tekoäly päättää vain luovuttaa.

Tekoäly tekee minmaxissa jokaiselle pelinpuun haaralle tarkistuksen, ettei oma kuningas päädy shakitukseen. Tämän tarkastuksen voisi tehdä vasta siinä vaiheessa kun kyseinen haara on katselussa, ja se nopeuttaisi algoritmia koska isoa osaa pelipuun haaroista ei edes katsella tarkemmin.

## Lähteet

https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning

Ohjelman yleisrakenne
Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)
Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen)
Työn mahdolliset puutteet ja parannusehdotukset
Lähteet


# Toteutusdokumentti

## Syvyysrajoitettu minimax

Pelilaudan tilannetta ylläpitävä tietorakenne on kaksi ulotteinen taulukko Nappula-luokkaisia olioita. Jokainen Nappula-luokkainen olio osaa palauttaa listan sallituista liikkeistään. Pelilaudan kunkin tilanteen pelipuun saa alkuun generoimalla oman puolen nappuloiden sallitut liikket. Sitten aloitetaan MiniMax-algoritmin pyöritys joka arvioi pelipuun tasoja vuorotellen kummankin eri pelaajan kannalta (tekoäly on max-pelaaja, vastustaja on min-pelaaja). Algoritmi olettaa vihollisen pelaavan aina ns. optimaalisti ja valitsee loppujenlopuksi polun joka päätyy optimaalitilanteessa tekoälyn kannalta parhaiten. *Alpha-beta karsintaa* tehdään minimaxin aikana, jonka avulla voidaan ohittaa pelipuun haarat jotka on todettu algoritmin logiikan kannalta turhiksi. MiniMax lopetetaan kun ollaan päästy tiettyyn haluttuun syvyyteen asti ja arvioidaan pelilaudan tilannetta valitun *heuristiikan* avulla.

## NegaMax

MiniMaxia on yksinkertaistettu hyödyntämällä tietoa, että max-pelaajan kannalta pelitilanteen arvo on sama kuin negaatio pelitilanteen arvosta min-pelaajan kannalta. Tässä yritetään siis aina vuorotellen maksimoida vastustajan arvon negaatiota. (Ei siis tarvitse erikseen ohjelmoida max ja min pelaajien logiikoita, vaan samalla koodilla saadaan hoideltua molemmat)

## Alpha-beta karsinta

Alpha-beta karsinnan avulla voidaan ohittaa minimax:in kannalta turhia pelipuun haaroja. Haarojen ohitus tapahtuu kun huomataan, että haara on **varmasti huonompi**, kuin jokin muu saman tason haara. 

## Heuristiikka

Pelipuun läpikäynti pohjasyvyyteen asti minimaxilla kestäisi aivan liian kauan shakkipelissä, sillä pelipuun koon kasvu on eksponentiaalista luokkaa. Maksimi-syvyyden saavutettua arvioidaan kuinka hyödyllinen pelitilanne on tekoälyn kannalta. 

## Mahdolliset puutteet ja parannusehdotukset

Tekoäly ei osaa tehdä ohestalyöntiä[2] (sotilaan erikoisliike) eikä tornitusta[3] (kuninkaan erikoisliike). Tekoäly päättää siis luovuttaa, jos ohestalyönti olisi ainut sallittu siirto. Tornitus on hieman erilainen liike, sillä sitä ei voida tehdä jos kuningas ei saa liikkua tavallisesti samaan suuntaan. Eli tornitus on mahdollinen vain jos kuninkaalla on olemassa toinen sallittu liike.  

Tekoäly tekee MiniMaxissa jokaiselle pelinpuun haaralle tarkistuksen, ettei oma kuningas päädy shakitukseen. Tämän tarkastuksen voisi tehdä vasta siinä vaiheessa kun kyseinen haara on katselussa, ja se nopeuttaisi algoritmia koska isoa osaa pelipuun haaroista ei edes katsella tarkemmin.

Heuristiikka on tällä hetkellä erittäin yksinkertainen ja varsinkin pelin alussa luulee kaikkia pelitilanteita samanarvoisiksi.

## Lähteet

[1] https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
[2] https://fi.wikipedia.org/wiki/Ohestaly%C3%B6nti
[3] https://fi.wikipedia.org/wiki/Tornitus
# Testausdokumentti

## Mitä on testattu

Eri nappuloiden mahdollisten siirtojen generointia on testattu useassa eri esimerkkitilanteessa. Testeissä on katsottu tavallisten sekä erikoissiirtojen (ei tornitusta eikä ohestalyöntiä) generointia, tähän sisältyy myös siirrot joilla syödään vastapelaajan nappuloita. On myös testattu ettei vahingossa generoida duplikaatteja siirtoja.

Pelilaudan luonnin oikeellisuus on testattu. Testit kattavat myös siirtojen prosessoinnin, joka takaa että tekoäly ei sekoa pelin aikana. 

Ruutujen ja siirtojen luonti sekä metodit on testattu.

SiirtoLista pohjautuu taulukkoon, jonka takia taulukon kasvattaminen tarvittaessa ja indeksin oikeellisuus on siinä testauksen keskipisteenä.


## Millaisilla syötteillä testaus tehtiin

Nappuloiden testeissä syötteinä on jokin pelilaudan tilanne ja siellä tarkasteltavana oleva nappula. Nappulan generoituja liikkeitä tarkastellaan ja katsotaan onko ne oikein.

Pelilaudan testauksessa syötteenä on laudan alkutilanne ja tarkasteltava siirto. Laudan uutta tilaa verrataan odotettuun tilaan.



## Miten on testattu

Testit on tehty JUnit:illa yksikkötesteinä.  


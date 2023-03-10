Proca Andreea-Dana
332CC
Tema 2 APD BONUS

    Pentru citirea din fisierul de comenzi in paralel in main am pornit P thread-uri
Thread1 si le-am adaugat in coada inQueue1. Fiecare thread citeste cate o linie din
fisier pana cand se ajunge la finalul acestuia si creeaza threaduri Thread2 pentru
fiecare linie citita(comanda). Toate threadurile folosesc acelasi buffered reader
"reader1" care face citire deja syncronized.

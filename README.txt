Proca Andreea-Dana
332CC
Tema 2 APD

Am utilizat 3 clase:
-> clasa Tema2 contine metoda main unde se primesc ca parametrii numarul de threaduri
   maxim P si directorul in care se gasesc fisierele de input, sunt instantiate
   - doua executor service: tpe pentru thread-urile Thread1 si tpe2 pentru
   thread-urile Thread2
   - doua atomic integers care sunt folosite drept cozi pentru taskuri: inQueue1
   pentru thread-urile Thread1 si inQueue2 pentru thread-urile Thread2
   - un file pentru directorul cu fisierele de input pe care le extrag in vectorul
   de File "in"
   - doua buffered reader pentru cele doua fisiere de input: reader1 pentru cel
   cu comenzi si reader2 pentru cel cu produse
   - creez fisierele de output "orders_out.txt" si "order_products_out.txt" care sunt
   ordersOut si productsOut
   - doua buffered writer pentru cele doua fisiere de output: writer1 pentru cel
   cu comenzi si writer2 pentru cel cu produse
    Pornesc P thread-uri Thread1 si le adaug in coada inQueue1. Acestea vor citi
  in paralel din fisierul de comenzi. Cat timp cele doua instante Executor Service
  nu s-au oprit,se asteapta; cand se opresc, se inchid readers si writers.

-> clasa Thread1 se ocupa de comenzi -> citeste din fisierul de orders folosind
   reader-ul primit ca parametru cat timp fisierul nu este gol. Cand citeste o comanda,
   daca numarul de produse din ea este diferit de 0, creeaza un thread Thread2 care
   se va ocupa de gasirea produselor corespunzatoare comenzii caruia ii transmite si
   un reader nou creat; incrementeaza inQueue2 pentru a adauga noul task in coada
   de taskuri pentru thread-urile Thread2. Apoi, citeste o noua linie(comanda).
   Verifica daca coada inQueue1 este goala, daca da, se opreste instanta Executor
   Service corespunzatoare threadurilor Thread1.

-> clasa Thread1 se ocupa de produse -> cat timp nu a gasit toate produsele din
   comanda data, citeste cate o linie din fisierul de products, daca produsul
   citit face parte din comanda data, numarul de produse de cautat scade si se
   scrie in fisierul "order_products_out.txt" produsul si "shipped".
   Cand au fost gasite toate produsele din comanda curenta, readerul threadului
   curent este inchis si se scrie in fisierul "orders_out.txt" comanda si "shipped".
   Verifica daca coada inQueue2 este goala, daca da, se opreste instanta Executor
   Service corespunzatoare threadurilor Thread2.
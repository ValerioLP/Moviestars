# Moviestars
Lo scopo di questa esercitazione è quella di creare una Java Application per la gestione di un cinema multisala, con un’interfaccia testuale per la gestione delle informazioni sui film.\n
Di seguito le tabelle con i campi definite durante la fase di progettazione:\n
FILM (CodFilm, Titolo, AnnoProduzione, Nazionalità, Regista, Attori, Genere)\n
PROIEZIONI (CodProiezione, CodFilm*, CodSala*, Incasso, DataProiezione)\n
SALE (CodSala, Posti, Nome, Città)\n
ATTORI (CodAttore, Nome, AnnoNascita, Nazionalità)\n
RECITA (CodAttore*, CodFilm*)\n

L’applicazione dovrà avere il seguente menu testuale:\n
1-Inserisci dati Film\n
2-Inserisci dati Proiezioni\n
3-Inserisci dati Sale\n
4-Cerca sala da città\n
5-Cerca proiezione da id film\n
6-Elimina proiezione con id\n
7-Backup DB
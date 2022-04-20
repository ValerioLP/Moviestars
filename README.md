# Moviestars
Lo scopo di questa esercitazione è quella di creare una Java Application per la gestione di un cinema multisala, con un’interfaccia testuale per la gestione delle informazioni sui film.
Di seguito le tabelle con i campi definite durante la fase di progettazione:
• FILM (CodFilm, Titolo, AnnoProduzione, Nazionalità, Regista, Attori, Genere)
• PROIEZIONI (CodProiezione, CodFilm*, CodSala*, Incasso, DataProiezione)
• SALE (CodSala, Posti, Nome, Città)
• ATTORI (CodAttore, Nome, AnnoNascita, Nazionalità);
• RECITA (CodAttore*, CodFilm*)

• L’applicazione dovrà avere il seguente menu testuale:

1-Inserisci dati Film
2-Inserisci dati Proiezioni
3-Inserisci dati Sale
4-Cerca sala da città
5-Cerca proiezione da id film
6-Elimina proiezione con id
7-Backup DB# Moviestars

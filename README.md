# Moviestars
Lo scopo di questa esercitazione è quella di creare una Java Application per la gestione di un cinema multisala, con un’interfaccia testuale per la gestione delle informazioni sui film.<br />
Di seguito le tabelle con i campi definite durante la fase di progettazione:<br />
• FILM (CodFilm, Titolo, AnnoProduzione, Nazionalità, Regista, Attori, Genere)<br />
• PROIEZIONI (CodProiezione, CodFilm*, CodSala*, Incasso, DataProiezione)<br />
• SALE (CodSala, Posti, Nome, Città)<br />
• ATTORI (CodAttore, Nome, AnnoNascita, Nazionalità)<br />
• RECITA (CodAttore*, CodFilm*)<br />
<br />
• L’applicazione dovrà avere il seguente menu testuale:<br />
<br />
1-Inserisci dati Film<br />
2-Inserisci dati Proiezioni<br />
3-Inserisci dati Sale<br />
4-Cerca sala da città<br />
5-Cerca proiezione da id film<br />
6-Elimina proiezione con id<br />
7-Backup DB# Moviestars

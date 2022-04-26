package test;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import prepared_statement.dao.FilmDAO_PreparedStatement;
import prepared_statement.dao.ProiezioniDAO_PreparedStatement;
import prepared_statement.dao.SaleDAO_PreparedStatement;
import prepared_statement.util.Backup;
import tables.Film;
import tables.Genere;
import tables.Proiezioni;
import tables.Sale;

public class MainClass {
	
	public static void main(String[] args) throws IOException {
		FilmDAO_PreparedStatement filmDAO = new FilmDAO_PreparedStatement();
		ProiezioniDAO_PreparedStatement proiezioniDAO = new ProiezioniDAO_PreparedStatement();
		SaleDAO_PreparedStatement salaDAO = new SaleDAO_PreparedStatement();

		Scanner keyboard = new Scanner(System.in);
		boolean result;

		printMenu();

		int userCommand = keyboard.nextInt();

		switch (userCommand) {
		case 0:
			proiezioniDAO.deleteAllProiezioni();
			filmDAO.deleteAllFilms();
			salaDAO.deleteAllSale();
			filmDAO.insertFilmAll(readFilmsFromData()); 
			salaDAO.insertSalaAll(readSaleFromData());
			proiezioniDAO.insertProiezioniAll(readProiezioniFromData()); 
		case 1:
			result = filmDAO.insertFilmAll(readFilmsFromData());
			System.out.println("Film inserito: " + result);
			break;
		case 2:
			result = proiezioniDAO.insertProiezioniAll(readProiezioniFromData());
			System.out.println("Proiezione inserita: " + result);
			break;
		case 3:
			result = salaDAO.insertSalaAll(readSaleFromData());
			System.out.println("Sala inserito: " + result);
			break;
		case 4:
			System.out.println("Inserisci nome citta per la ricerca");
			keyboard.nextLine();
			List<Sale> sale = salaDAO.getSaleByCitta(keyboard.nextLine());
			for (Sale s_by_citta : sale)
				System.out.println(s_by_citta.toString());
			break;
		case 5:
			System.out.println("Inserisci codice film per ricercare proiezioni");
			keyboard.nextLine();
			List<Proiezioni> proiezioni = proiezioniDAO.getProiezioniByFilm(keyboard.nextInt());
			for (Proiezioni p_by_film : proiezioni)
				System.out.println(p_by_film.toString());

			break;
		case 6:
			System.out.println("Inserisci codice proiezione da eliminare");
			keyboard.nextLine();
			proiezioniDAO.deleteProiezioni(keyboard.nextInt());
			break;
		case 7:
			System.out.println("Inserisci il path dove creare il file di backup");
			keyboard.nextLine();
			String input1 = keyboard.nextLine();
			System.out.println("Inserisci il nome del file di backup");
			String input2 = keyboard.nextLine();
			Backup.backupOnTextFile(input1,input2);
			break;
		default:
			printMenu();
			break;
		}

		keyboard.close();
	}

	public static List<Film> readFilmsFromData() {
		List<Film> films = new ArrayList<Film>();

		films.add(new Film("Titolo_1", new Date(2022), "Nazionalita_1", Genere.AZIONE));
		films.add(new Film("Titolo_2", new Date(2004), "Nazionalita_2", Genere.AUTOBIOGRAFICO));
		films.add(new Film("Titolo_3", new Date(2020), "Nazionalita_3", Genere.AVVENTURA));
		films.add(new Film("Titolo_4", new Date(2021), "Nazionalita_4", Genere.COMMEDIA));
		films.add(new Film("Titolo_5", new Date(2004), "Nazionalita_5", Genere.DARK));
		films.add(new Film("Titolo_6", new Date(2006), "Nazionalita_1", Genere.FANTASY));
		films.add(new Film("Titolo_7", new Date(2008), "Nazionalita_2", Genere.FANTASCIENZA));
		films.add(new Film("Titolo_8", new Date(2012), "Nazionalita_3", Genere.GIALLO));
		films.add(new Film("Titolo_9", new Date(2013), "Nazionalita_4", Genere.HORROR));
		films.add(new Film("Titolo_10", new Date(2012), "Nazionalita_5", Genere.THRILLER));
		films.add(new Film("Titolo_11", new Date(2015), "Nazionalita_1", Genere.FANTASCIENZA));
		films.add(new Film("Titolo_12", new Date(2017), "Nazionalita_2", Genere.FANTASY));
		films.add(new Film("Titolo_13", new Date(2018), "Nazionalita_3", Genere.AUTOBIOGRAFICO));
		films.add(new Film("Titolo_14", new Date(2019), "Nazionalita_4", Genere.FANTASCIENZA));
		films.add(new Film("Titolo_15", new Date(2019), "Nazionalita_5", Genere.AZIONE));

		return films;
	}

	public static List<Sale> readSaleFromData() {
		List<Sale> sale = new ArrayList<Sale>();

		sale.add(new Sale(1000, "Sala_2", "Pescara"));
		sale.add(new Sale(1000, "Sala_4", "Pescara"));
		sale.add(new Sale(1000, "Sala_6", "Pescara"));
		sale.add(new Sale(1000, "Sala_8", "Roma"));
		sale.add(new Sale(1000, "Sala_9", "Milano"));
		sale.add(new Sale(1000, "Sala_2", "Firenze"));
		sale.add(new Sale(1000, "Sala_4", "Firenze"));
		sale.add(new Sale(1000, "Sala_6", "Napoli"));
		sale.add(new Sale(1000, "Sala_8", "Catania"));
		sale.add(new Sale(1000, "Sala_9", "Cagliari"));
		sale.add(new Sale(1000, "Sala_2", "Roma"));
		sale.add(new Sale(1000, "Sala_4", "Roma"));
		sale.add(new Sale(1000, "Sala_6", "Pescara"));
		sale.add(new Sale(1000, "Sala_8", "Torino"));
		sale.add(new Sale(1000, "Sala_9", "Milano"));

		return sale;
	}

	/*
	 * Proiezioni contieni riferimenti esterni verso Sala e Film, non so quali ID
	 * però sono presenti nel DB. Devo leggere il SET di chiavi da Sala e Film cosi
	 * posso usare degli ID esisenti nel DB. (Ricorda sono presenti autonumeranti
	 * nei codici)
	 */
	public static List<Proiezioni> readProiezioniFromData() {
		List<Proiezioni> proiezioni = new ArrayList<Proiezioni>();
		
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 123456.89, new Date(2022)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 35623434, new Date(2012)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 32552343, new Date(2023)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 83274598, new Date(2012)));		
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 32678432, new Date(2014)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 468794358, new Date(2015)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 13578583, new Date(2022)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 123423, new Date(2021)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 3563, new Date(2020)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 123622623, new Date(2020)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 123434334, new Date(2020)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 75675675, new Date(2010)));		
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 93285827, new Date(2008)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 345343, new Date(2009)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 4689464, new Date(2010)));
		proiezioni.add(new Proiezioni(getRandomFilm(), getRandomSala(), 4234244, new Date(2010)));

		return proiezioni;
	}

	public static void printMenu() {
		System.out.println("------------------------------");
		System.out.println("0 - Popola intero DB");
		System.out.println("1 - Inserisci Film");
		System.out.println("2 - Inserisci Proiezione");
		System.out.println("3 - Inserisci Sala");
		System.out.println("4 - Cerca sala per città");
		System.out.println("5 - Cerca proiezione per codice film");
		System.out.println("6 - Elimina proiezione per codice proiezione");
		System.out.println("7 - Backup DataBase");
		System.out.println("------------------------------");
	}

	private static Sale getRandomSala() {
		SaleDAO_PreparedStatement salaDAO = new SaleDAO_PreparedStatement();
		List<Integer> sale_keys = new ArrayList<Integer>();
		for (Sale f : salaDAO.getAllSale())
			sale_keys.add(f.getCodice());

		int indexSala = extractRandomNumber(sale_keys.size());
		return salaDAO.getSalaByID(sale_keys.get(indexSala));
	}

	private static Film getRandomFilm() {
		FilmDAO_PreparedStatement filmDAO = new FilmDAO_PreparedStatement();
		List<Integer> film_keys = new ArrayList<Integer>();
		for (Film f : filmDAO.getAllFilms())
			film_keys.add(f.getCodice());

		int indexFilm = extractRandomNumber(film_keys.size());
		return filmDAO.getFilmByID(film_keys.get(indexFilm));
	}

	private static int extractRandomNumber(int max) {
		double min = 0.0;
		return (int) Math.floor(Math.random() * (Math.floor(max - 1) - min + 1) + min);
	}
}

package test;

import java.io.IOException;
import java.sql.Date;

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
		SaleDAO_PreparedStatement saleDAO = new SaleDAO_PreparedStatement();
		ProiezioniDAO_PreparedStatement proiezioniDAO = new ProiezioniDAO_PreparedStatement();
		
		//INSERT FILM
		filmDAO.insertFilm(new Film("Jurassic Park", new Date(1993), "USA", Genere.FANTASCIENZA));
		
		//INSERT SALE
		saleDAO.insertSala(new Sale(160, "Maximo", "Parigi"));
		saleDAO.insertSala(new Sale(150, "Bella Pe Te", "Lisbona"));
		
		//INSERT PROIEZIONI
		proiezioniDAO.insertProiezione(new Proiezioni(filmDAO.getAllFilms().get(2).getCodice(), 
				saleDAO.getAllSale().get(2).getCodice(), 15000000, new Date(2021)));		
		
		System.out.println(saleDAO.getSaleByCitta("Roma"));
		
		Backup.backupOnTextFile("G:\\Uni\\Primo_Anno\\Java\\MovieStars\\backup");
	}

}

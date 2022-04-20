package prepared_statement.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import prepared_statement.dao.FilmDAO_PreparedStatement;
import prepared_statement.dao.ProiezioniDAO_PreparedStatement;
import prepared_statement.dao.SaleDAO_PreparedStatement;

public class Backup {
	
    public static void backupOnTextFile(String fileName) throws IOException {
        try {
        	FilmDAO_PreparedStatement filmDAO = new FilmDAO_PreparedStatement();
        	Files.write(Paths.get(fileName), filmDAO.getAllFilms().toString().getBytes(), StandardOpenOption.CREATE);
        	System.out.println("Tutti i film sono stati scritti sul file");
        	Files.write(Paths.get(fileName), System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
        	
        	SaleDAO_PreparedStatement saleDAO = new SaleDAO_PreparedStatement();
        	Files.write(Paths.get(fileName), saleDAO.getAllSale().toString().getBytes(), StandardOpenOption.APPEND);
        	System.out.println("Tutte le sale sono state scritte sul file");
        	Files.write(Paths.get(fileName), System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
        	
        	ProiezioniDAO_PreparedStatement proiezioniDAO = new ProiezioniDAO_PreparedStatement();
        	Files.write(Paths.get(fileName), proiezioniDAO.getAllProiezioni().toString().getBytes(), StandardOpenOption.APPEND);
        	System.out.println("Tutte le proiezioni sono state scritte sul file");
        	Files.write(Paths.get(fileName), System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
        	System.out.println("Errore nella creazione del file");
        }
    }

}

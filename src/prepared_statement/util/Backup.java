package prepared_statement.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;

import prepared_statement.dao.FilmDAO_PreparedStatement;
import prepared_statement.dao.ProiezioniDAO_PreparedStatement;
import prepared_statement.dao.SaleDAO_PreparedStatement;

public class Backup {
	
    public static void backupOnTextFile(String path, String fileName) throws IOException {
        try {      
        	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        	FilmDAO_PreparedStatement filmDAO = new FilmDAO_PreparedStatement();
        	String newPath = path+"\\"+fileName+timestamp.toString().replaceAll(":","_" );
        	
        	Files.write(Paths.get(newPath), filmDAO.getAllFilms().toString().getBytes(), StandardOpenOption.CREATE);
        	System.out.println("Tutti i film sono stati scritti sul file");
        	Files.write(Paths.get(newPath), System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
        	
        	SaleDAO_PreparedStatement saleDAO = new SaleDAO_PreparedStatement();
        	Files.write(Paths.get(newPath), saleDAO.getAllSale().toString().getBytes(), StandardOpenOption.APPEND);
        	System.out.println("Tutte le sale sono state scritte sul file");
        	Files.write(Paths.get(newPath), System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
        	
        	ProiezioniDAO_PreparedStatement proiezioniDAO = new ProiezioniDAO_PreparedStatement();
        	Files.write(Paths.get(newPath), proiezioniDAO.getAllProiezioni().toString().getBytes(), StandardOpenOption.APPEND);
        	System.out.println("Tutte le proiezioni sono state scritte sul file");
        	Files.write(Paths.get(newPath), System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
        	System.out.println("Errore nella creazione del file");
        }
    }

}

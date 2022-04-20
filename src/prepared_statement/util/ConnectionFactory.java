package prepared_statement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Classe factory per stabilire e chiudere la connessione al database
 */
public class ConnectionFactory {
	
	private static Connection connection;
	private static final String URL = "jdbc:postgresql://localhost:5432/moviestars2.0";
	private static final String USER = "postgres";
	private static final String PASSWORD = "admin";

	/*
	 * Metodo che ritorna la connessione al database
	 */
	public static Connection getConnection() {
		//Se ho gia una connessione la ritorno, senza crearne una nuova ogni volta
		if(connection != null) return connection;
		//altrimenti creo una nuova connessione al database
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connessione al database stabilita.");
			
		} catch(SQLException e) {
			System.out.println("Connessione al database fallita!");
			e.printStackTrace();
		}
		return connection;
	}
	
	/*
	 * Metodo che chiude la connessione al database
	 */
	public static void closeConnection() {
		if(connection != null)
			try {
				connection.close();
				connection = null;
			} catch(SQLException e) {
				System.out.println("Errore nella chiusura del database!");
				e.printStackTrace();
			}
		
	}
}

package prepared_statement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import prepared_statement.util.ConnectionFactory;
import tables.Proiezioni;

public class ProiezioniDAO_PreparedStatement {
	
	/*
	 * Metodo per l'inserimento di tuple nella tabella proiezioni
	 */
	public boolean insertProiezione(Proiezioni proiezione) {
		PreparedStatement psInsertProiezione = null;
		int row_affected = 0;
		boolean row_inserted = true;
		
		try {
			//Costruisco la query di insert per la proiezione
			String insertProiezione = "INSERT INTO proiezioni(codice_film, codice_sala, incasso, data_proiezione) "
					+ "VALUES(?,?,?,?)";
			psInsertProiezione = ConnectionFactory.getConnection().prepareStatement(insertProiezione);
			psInsertProiezione.setInt(1, proiezione.getCodice_film());
			psInsertProiezione.setInt(2, proiezione.getCodice_sala());
			psInsertProiezione.setDouble(3, proiezione.getIncasso());
			psInsertProiezione.setDate(4, proiezione.getData_proiezione());
			//Eseguo la query di insert appena costruita
			row_affected = psInsertProiezione.executeUpdate();
			
			//Controllo se la query è stata eseguita con successo o meno
			if(row_affected > 0) System.out.println("Inserimento avvenuto con successo");
			else {
				System.out.println("Non e' stata inserita nessuna tupla!");
				row_inserted = false;
			}
		} catch(SQLException e) {
			System.out.println("Errore nell'inserimento della proiezione");
			e.printStackTrace();
			row_inserted = false;
		} finally {	ConnectionFactory.closeConnection(); }
		
		return row_inserted;
	}
	
	/*
	 * Metodo per la ricerca delle proiezioni a partire dall'id del film
	 */
	public List<Proiezioni> getProiezioniByFilm(int id) {
		PreparedStatement psProiezioniByFilm = null;
		//Creo il set di tipo ResultSet che sarà il risultato della query di selezione
		ResultSet resultSet = null;
		Proiezioni proiezione = null;
		//Creo la lista finale di tutte le proiezioni che matcheranno con la query da restituire in output
		List<Proiezioni> proiezioni = new ArrayList<>();
		
		try {
			//Costruisco la query di selezione delle proiezioni a partire dal film
			String selectProiezioni = "select * from proiezioni where codice_film = ?";
			psProiezioniByFilm = ConnectionFactory.getConnection().prepareStatement(selectProiezioni);
			psProiezioniByFilm.setInt(1, id);
			//Eseguo la query di selezione appena costruita
			resultSet = psProiezioniByFilm.executeQuery();
			
			while(resultSet.next()) {
				proiezione = new Proiezioni(resultSet.getInt("codice"), resultSet.getInt("codice_film"), 
						resultSet.getInt("codice_sala"), resultSet.getDouble("incasso"), 
						resultSet.getDate("data_proiezione"));
				proiezioni.add(proiezione);
			}
			
		} catch(SQLException e) {
			System.out.println("Errore nell'esecuzione della query getProiezioniByFilm");
			e.printStackTrace();
		}
		return proiezioni;
	}
	
	/*
	 * Metodo per l'eliminazione di una tupla da proiezioni
	 */
	public boolean deleteProiezione(Proiezioni proiezione) {
		PreparedStatement psDeleteProiezione = null;
		int row_affected = 0;
		boolean row_deleted = true;

		try {
			//Costruisco la query di delete per la proiezione
			String deleteEmployee = "DELETE FROM proiezioni WHERE id = ?";
			psDeleteProiezione = ConnectionFactory.getConnection().prepareStatement(deleteEmployee);
			psDeleteProiezione.setInt(1, proiezione.getCodice());
			//Eseguo la query di delete appena costruita
			row_affected = psDeleteProiezione.executeUpdate();

			//Controllo se la query è stata eseguita con successo o meno
			if (row_affected > 0) System.out.println("Cancellazione avvenuto con successo");
			else {
				System.out.println("Non e' stata cancellata nessuna tupla!");
				row_deleted = false;
			}

		} catch (SQLException e1) {
			System.out.println("Errore nella cancellazione della proiezione");
			e1.printStackTrace();
			row_deleted = false;
		} finally {
			ConnectionFactory.closeConnection();
		}

		return row_deleted;
	}
	
	public List<Proiezioni> getAllProiezioni() {
		Statement st = null;
		ResultSet rs = null;
		Proiezioni proiezione;
		List<Proiezioni> proiezioni = new ArrayList<>();

		try {
			st = ConnectionFactory.getConnection().createStatement();
			rs = st.executeQuery("select * from proiezioni");

			while (rs.next()) {
				proiezione = new Proiezioni(rs.getInt("codice"), rs.getInt("codice_film"),rs.getInt("codice_sala"), 
						rs.getDouble("incasso"), rs.getDate("data_proiezione"));
				proiezioni.add(proiezione);
			}
		} catch (SQLException e) {
			System.out.println("Errore nella selezione di tutte le proiezioni");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}

		return proiezioni;
	}
}

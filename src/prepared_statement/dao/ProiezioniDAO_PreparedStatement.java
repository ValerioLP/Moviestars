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
			psInsertProiezione.setInt(1, proiezione.getFilm().getCodice());
			psInsertProiezione.setInt(2, proiezione.getSala().getCodice());
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
		FilmDAO_PreparedStatement filmDAO = new FilmDAO_PreparedStatement();
		SaleDAO_PreparedStatement salaDAO = new SaleDAO_PreparedStatement();
		
		try {
			//Costruisco la query di selezione delle proiezioni a partire dal film
			String selectProiezioni = "select * from proiezioni where codice_film = ?";
			psProiezioniByFilm = ConnectionFactory.getConnection().prepareStatement(selectProiezioni);
			psProiezioniByFilm.setInt(1, id);
			//Eseguo la query di selezione appena costruita
			resultSet = psProiezioniByFilm.executeQuery();
			
			while(resultSet.next()) {
				proiezione = new Proiezioni();
				proiezione.setCodice(resultSet.getInt("codice"));
				proiezione.setData_proiezione(resultSet.getDate("data_proiezione"));
				proiezione.setIncasso(resultSet.getDouble("incasso"));

				int codFilm = resultSet.getInt("codice_film");
				proiezione.setFilm(filmDAO.getFilmByID(codFilm));

				int codSala = resultSet.getInt("codice_sala");
				proiezione.setSala(salaDAO.getSalaByID(codSala));

				proiezioni.add(proiezione);
			}
			
		} catch(SQLException e) {
			System.out.println("Errore nell'esecuzione della query getProiezioniByFilm");
			e.printStackTrace();
		}
		return proiezioni;
	}
	
	public boolean insertProiezioniAll(List<Proiezioni> proiezioni) {
		boolean rows_inserted = true;
		for (Proiezioni p : proiezioni) {
			rows_inserted = insertProiezione(p);

			if (rows_inserted == false)
				return false;
		}

		return rows_inserted;
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
	
	public boolean deleteAllProiezioni() {
		PreparedStatement psDeleteProiezioni = null;
		int row_affected = 0;
		boolean row_deleted = true;

		try {
			String deleteProiezione = "DELETE FROM proiezioni";
			psDeleteProiezioni = ConnectionFactory.getConnection().prepareStatement(deleteProiezione);
			row_affected = psDeleteProiezioni.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Eccezione durante cancellazione proiezione");
			e1.printStackTrace();
			row_deleted = false;
		} finally {
			ConnectionFactory.closeConnection();
		}
		return row_deleted; 
	}
	
	public List<Proiezioni> getAllProiezioni() {
		FilmDAO_PreparedStatement filmDAO = new FilmDAO_PreparedStatement();
		SaleDAO_PreparedStatement salaDAO = new SaleDAO_PreparedStatement();

		Statement st = null;
		ResultSet rs = null;
		Proiezioni proiezione;
		List<Proiezioni> proiezioni = new ArrayList<>();

		try {
			st = ConnectionFactory.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM proiezioni");

			while (rs.next()) {
				proiezione = new Proiezioni();				
				proiezione.setCodice(rs.getInt("codice"));
				proiezione.setData_proiezione(rs.getDate("data_proiezione"));
				proiezione.setIncasso(rs.getDouble("incasso"));

				int codFilm = rs.getInt("codice_film");
				proiezione.setFilm(filmDAO.getFilmByID(codFilm));

				int codSala = rs.getInt("codice_sala");
				proiezione.setSala(salaDAO.getSalaByID(codSala));

				proiezioni.add(proiezione);
			}
		} catch (SQLException e) {
			System.out.println("Connection error");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}

		return proiezioni;

	}
	
	public boolean deleteProiezioni(int codProiezione) {
		PreparedStatement psDeleteProiezioni = null;
		int row_affected = 0;
		boolean row_deleted = true;

		try {
			String deleteProiezione = "DELETE FROM proiezioni WHERE codice = ?";
			psDeleteProiezioni = ConnectionFactory.getConnection().prepareStatement(deleteProiezione);
			psDeleteProiezioni.setInt(1, codProiezione);
			row_affected = psDeleteProiezioni.executeUpdate();

			if (row_affected > 0)
				System.out.println("Cancellazione avvenuto con successo");
			else {
				System.out.println("ERROR: nessuna riga cancellata");
				row_deleted = false;
			}

		} catch (SQLException e1) {
			System.out.println("Eccezione durante cancellazione proiezione");
			e1.printStackTrace();
			row_deleted = false;
		} finally {
			ConnectionFactory.closeConnection();
		}

		return row_deleted;
	}
}

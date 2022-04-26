package prepared_statement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import prepared_statement.util.ConnectionFactory;
import tables.Film;
import tables.Genere;

public class FilmDAO_PreparedStatement {
	
	/*
	 * Metodo per l'inserimento di tuple nella tabella film
	 */
	public boolean insertFilm(Film film) {
		PreparedStatement psInsertFilm = null;
		int row_affected = 0;
		boolean row_inserted = true;
		
		try {
			//Costruisco la query di insert per il film
			String insertFilm = "INSERT INTO film(titolo, anno_produzione, nazionalita, genere) "
					+ "VALUES(?,?,?,?)";
			psInsertFilm = ConnectionFactory.getConnection().prepareStatement(insertFilm);
			psInsertFilm.setString(1, film.getTitolo());
			psInsertFilm.setDate(2, film.getAnno_produzione());
			psInsertFilm.setString(3, film.getNazionalita());
			psInsertFilm.setString(4, film.getGenere().toString());
			//Eseguo la query di insert appena costruita
			row_affected = psInsertFilm.executeUpdate();
			
			//Controllo se la query è stata eseguita con successo o meno
			if(row_affected > 0) System.out.println("Inserimento avvenuto con successo");
			else {
				System.out.println("Non e' stata inserita nessuna tupla!");
				row_inserted = false;
			}
		} catch(SQLException e) {
			System.out.println("Errore nell'inserimento del film");
			e.printStackTrace();
			row_inserted = false;
		} finally {	ConnectionFactory.closeConnection(); }
		
		return row_inserted;
	}
	
	public boolean insertFilmAll(List<Film> films) {
		boolean rows_inserted = true;
		for (Film f : films) {
			rows_inserted = insertFilm(f);

			if (rows_inserted == false)
				return false;
		}

		return rows_inserted;
	}
	
	public List<Film> getAllFilms() {
		Statement st = null;
		ResultSet rs = null;
		Film film;
		List<Film> films = new ArrayList<>();

		try {
			st = ConnectionFactory.getConnection().createStatement();
			rs = st.executeQuery("select * from film");

			while (rs.next()) {
				film = new Film(rs.getInt("codice"), rs.getString("titolo"),rs.getDate("anno_produzione"), 
						rs.getString("nazionalita"), Genere.valueOf(rs.getString("genere")));
				films.add(film);
			}
		} catch (SQLException e) {
			System.out.println("Errore nella selezione di tutti i film");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}

		return films;
	}

	public Film getFilmByID(int codFilm) {
		PreparedStatement psFilmByID = null;
		ResultSet rs = null;
		Film film = null;

		try {
			String getFilmByID = "select * from film where codice = ?";
			psFilmByID = ConnectionFactory.getConnection().prepareStatement(getFilmByID);
			psFilmByID.setInt(1, codFilm);
			rs = psFilmByID.executeQuery();

			if (rs.next()) {
				film = new Film(rs.getInt("codice"), rs.getString("titolo"),rs.getDate("anno_produzione"), 
						rs.getString("nazionalita"), Genere.valueOf(rs.getString("genere")));
			}

		} catch (SQLException e) {
			System.out.println("Connection error");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}

		return film;
	}
	
	public boolean deleteAllFilms() {
		PreparedStatement psDeleteFilms = null;
		int row_affected = 0;
		boolean row_deleted = true;

		try {
			String DeleteFilms = "DELETE FROM film";
			psDeleteFilms = ConnectionFactory.getConnection().prepareStatement(DeleteFilms);
			row_affected = psDeleteFilms.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Eccezione durante cancellazione film");
			e1.printStackTrace();
			row_deleted = false;
		} finally {
			ConnectionFactory.closeConnection();
		}
		return row_deleted; 
	}

}

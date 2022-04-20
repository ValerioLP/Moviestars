package prepared_statement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import prepared_statement.util.ConnectionFactory;
import tables.Sale;

public class SaleDAO_PreparedStatement {
	
	/*
	 * Metodo per l'inserimento di tuple nella tabella sala
	 */
	public boolean insertSala(Sale sala) {
		PreparedStatement psInsertSala = null;
		int row_affected = 0;
		boolean row_inserted = true;
		
		try {
			//Costruisco la query di insert per la sala
			String insertSala = "INSERT INTO sale(numero_posti, nome, citta) "
					+ "VALUES(?,?,?)";
			psInsertSala = ConnectionFactory.getConnection().prepareStatement(insertSala);
			psInsertSala.setInt(1, sala.getNumero_posti());
			psInsertSala.setString(2, sala.getNome());;
			psInsertSala.setString(3, sala.getCittà());
			//Eseguo la query di insert appena costruita
			row_affected = psInsertSala.executeUpdate();
			
			//Controllo se la query è stata eseguita con successo o meno
			if(row_affected > 0) System.out.println("Inserimento avvenuto con successo");
			else {
				System.out.println("Non e' stata inserita nessuna tupla!");
				row_inserted = false;
			}
		} catch(SQLException e) {
			System.out.println("Errore nell'inserimento della sala");
			e.printStackTrace();
			row_inserted = false;
		} finally {	ConnectionFactory.closeConnection(); }
		
		return row_inserted;
	}

	/*
	 * Metodo per la ricerca delle sale a partire dalla citta
	 */
	public List<Sale> getSaleByCitta(String citta) {
		PreparedStatement psSalaByCitta = null;
		//Creo il set di tipo ResultSet che sarà il risultato della query di selezione
		ResultSet resultSet = null;
		Sale sala = null;
		//Creo la lista finale di tutte le sale che matcheranno con la query da restituire in output
		List<Sale> sale = new ArrayList<>();
		
		try {
			//Costruisco la query di selezione delle sale a partire dalla citta
			String selectSale = "select * from sale where citta = ?";
			psSalaByCitta = ConnectionFactory.getConnection().prepareStatement(selectSale);
			psSalaByCitta.setString(1, citta);
			//Eseguo la query di selezione appena costruita
			resultSet = psSalaByCitta.executeQuery();
			
			while(resultSet.next()) {
				sala = new Sale(resultSet.getInt("codice"), resultSet.getInt("numero_posti"),
								resultSet.getString("nome"), resultSet.getString("citta"));
				sale.add(sala);
			}
			
		} catch(SQLException e) {
			System.out.println("Errore nell'esecuzione della query getSaleByCitta");
			e.printStackTrace();
		}
		return sale;
	}
	
	public List<Sale> getAllSale() {
		Statement st = null;
		ResultSet rs = null;
		Sale sala;
		List<Sale> sale = new ArrayList<>();

		try {
			st = ConnectionFactory.getConnection().createStatement();
			rs = st.executeQuery("select * from sale");

			while (rs.next()) {
				//int codice, int numero_posti, String nome, String citta
				sala = new Sale(rs.getInt("codice"), rs.getInt("numero_posti"),rs.getString("nome"), 
						rs.getString("citta"));
				sale.add(sala);
			}
		} catch (SQLException e) {
			System.out.println("Errore nella selezione di tutte le sale");
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection();
		}

		return sale;
	}
	
}

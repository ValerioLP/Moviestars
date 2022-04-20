package tables;

public class Recita {

	private int codice_cast;
	private int codice_film;
	
	public Recita(int codice_cast, int codice_film) {
		this.codice_cast = codice_cast;
		this.codice_film = codice_film;
	}

	/*
	 * Getter e Setter
	 */
	public int getCodice_cast() { return codice_cast; }
	public void setCodice_cast(int codice_cast) { this.codice_cast = codice_cast; }
	public int getCodice_film() { return codice_film; }
	public void setCodice_film(int codice_film) { this.codice_film = codice_film; }
	
	
	
}

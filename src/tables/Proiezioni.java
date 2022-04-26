package tables;

import java.sql.Date;


public class Proiezioni {
	
	private int codice;
	private Sale sala;
	private Film film;
	private double incasso;
	private Date data_proiezione;
	
	public Proiezioni(int codice, Film film, Sale sala, double incasso, Date data_proiezione) {
		this.codice = codice;
		this.film = film;
		this.sala = sala;
		this.incasso = incasso;
		this.data_proiezione = data_proiezione;
	}
	
	public Proiezioni(Film film, Sale sala, double incasso, Date data_proiezione) {
		this.film = film;
		this.sala = sala;
		this.incasso = incasso;
		this.data_proiezione = data_proiezione;
	}


	public Proiezioni() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Getter e Setter
	 */
	public int getCodice() { return codice; }
	public void setCodice(int codice) { this.codice = codice; }
	public Film getFilm() { return film; }
	public void setFilm(Film film) { this.film = film; }
	public Sale getSala() { return sala; }
	public void setSala(Sale sala) { this.sala = sala; }
	public double getIncasso() { return incasso; }
	public void setIncasso(double incasso) { this.incasso = incasso; }
	public Date getData_proiezione() { return data_proiezione; }
	public void setData_proiezione(Date data_proiezione) { this.data_proiezione = data_proiezione; }

	@Override
	public String toString() {
		return "Proiezioni [codice=" + codice + ", codice_film=" + film.getCodice() + ", codice_sala=" + sala.getCodice()
				+ ", incasso=" + incasso + ", data_proiezione=" + data_proiezione + "]";
	}
}

package tables;

import java.sql.Date;

public class Proiezioni {
	
	private int codice;
	private int codice_film;
	private int codice_sala;
	private double incasso;
	private Date data_proiezione;
	
	public Proiezioni(int codice, int codice_film, int codice_sala, double incasso, Date data_proiezione) {
		this.codice = codice;
		this.codice_film = codice_film;
		this.codice_sala = codice_sala;
		this.incasso = incasso;
		this.data_proiezione = data_proiezione;
	}
	
	public Proiezioni(int codice_film, int codice_sala, double incasso, Date data_proiezione) {
		this.codice_film = codice_film;
		this.codice_sala = codice_sala;
		this.incasso = incasso;
		this.data_proiezione = data_proiezione;
	}


	/*
	 * Getter e Setter
	 */
	public int getCodice() { return codice; }
	public void setCodice(int codice) { this.codice = codice; }
	public int getCodice_film() { return codice_film; }
	public void setCodice_film(int codice_film) { this.codice_film = codice_film; }
	public int getCodice_sala() { return codice_sala; }
	public void setCodice_sala(int codice_sala) { this.codice_sala = codice_sala; }
	public double getIncasso() { return incasso; }
	public void setIncasso(double incasso) { this.incasso = incasso; }
	public Date getData_proiezione() { return data_proiezione; }
	public void setData_proiezione(Date data_proiezione) { this.data_proiezione = data_proiezione; }

	@Override
	public String toString() {
		return "Proiezioni [codice=" + codice + ", codice_film=" + codice_film + ", codice_sala=" + codice_sala
				+ ", incasso=" + incasso + ", data_proiezione=" + data_proiezione + "]";
	}
}

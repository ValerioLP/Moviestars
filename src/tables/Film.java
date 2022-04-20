package tables;

import java.sql.Date;
import java.util.List;

public class Film {
	
	private int codice;
	private String titolo;
	private Date anno_produzione;
	private String nazionalita;
	
	private Cast regista;
	private List<Cast> attori;
	private Genere genere;
	
	public Film(int codice, String titolo, Date anno_produzione, String nazionalita, Genere genere) {
		this.codice = codice;
		this.titolo = titolo;
		this.anno_produzione = anno_produzione;
		this.nazionalita = nazionalita;
		this.genere = genere;
	}
	
	public Film(String titolo, Date anno_produzione, String nazionalita, Genere genere) {	
		this.titolo = titolo;
		this.anno_produzione = anno_produzione;
		this.nazionalita = nazionalita;
		this.genere = genere;
	}
	
	/*
	 * Getter e Setter
	 */
	public int getCodice() { return codice;	}
	public void setCodice(int codice) {	this.codice = codice; }
	public String getTitolo() {	return titolo; }
	public void setTitolo(String titolo) { this.titolo = titolo; }
	public Date getAnno_produzione() { return anno_produzione;	}
	public void setAnno_produzione(Date anno_produzione) {	this.anno_produzione = anno_produzione;	}
	public String getNazionalita() { return nazionalita; }
	public void setNazionalita(String nazionalita) { this.nazionalita = nazionalita; }
	public Genere getGenere() { return genere; }
	public void setGenere(Genere genere) { this.genere = genere; }
	
	/*
	 * Da lavorare
	 */
	public Cast getRegista() { return regista; }
	public void setRegista(Cast regista) { this.regista = regista; }
	public List<Cast> getAttori() { return attori; }
	public void setAttori(List<Cast> attori) { this.attori = attori; }
	
	@Override
	public String toString() {
		return "Film [codice=" + codice + ", titolo=" + titolo + "]";
	}

	
}

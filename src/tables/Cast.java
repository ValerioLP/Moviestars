package tables;

public class Cast {
	
	int codice;
	String nome;
	String cognome;
	
	public Cast(int codice, String nome, String cognome) {
		this.codice = codice;
		this.nome = nome;
		this.cognome = cognome;
	}

	/*
	 * Getter e Setter
	 */
	public int getCodice() { return codice; }
	public void setCodice(int codice) { this.codice = codice; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public String getCognome() { return cognome; }
	public void setCognome(String cognome) { this.cognome = cognome; }

	
}

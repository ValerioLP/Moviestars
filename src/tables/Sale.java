package tables;

public class Sale {
	
	private int codice;
	private int numero_posti;
	private String nome;
	private String citta;	

	public Sale(int codice, int numero_posti, String nome, String citta) {
		this.codice = codice;
		this.numero_posti = numero_posti;
		this.nome = nome;
		this.citta = citta;
	}
	
	public Sale(int numero_posti, String nome, String citta) {
		this.numero_posti = numero_posti;
		this.nome = nome;
		this.citta = citta;
	}

	/*
	 * Getter e Setter
	 */
	public int getCodice() { return codice; }
	public void setCodice(int codice) { this.codice = codice; }
	public int getNumero_posti() { return numero_posti; }
	public void setNumero_posti(int numero_posti) { this.numero_posti = numero_posti; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public String getCittà() { return citta; }
	public void setCittà(String citta) { this.citta = citta; }

	@Override
	public String toString() {
		return "Sale [codice=" + codice + ", nome=" + nome + "]";
	}

	
	
}

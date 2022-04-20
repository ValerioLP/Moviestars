package tables;

public enum Genere {

	FANTASY, FANTASCIENZA, AZIONE, AVVENTURA, COMMEDIA, AUTOBIOGRAFICO, 
	DRAMMATICO, HORROR, DARK, GIALLO, THRILLER;
	
	public String toString() {
		String result = "";
		switch(this) {
			case FANTASY : result = "FANTASY"; break;
			case FANTASCIENZA : result = "FANTASCIENZA"; break;
			case AZIONE : result = "AZIONE"; break;
			case AVVENTURA : result = "AVVENTURA"; break;
			case COMMEDIA : result = "COMMEDIA"; break;
			case AUTOBIOGRAFICO : result = "AUTOBIOGRAFICO"; break;
			case DRAMMATICO : result = "DRAMMATICO"; break;
			case HORROR : result = "HORROR"; break;
			case DARK : result = "DARK"; break;
			case GIALLO : result = "GIALLO"; break;
			case THRILLER : result = "THRILLER";
		}
		return result;
	}
}

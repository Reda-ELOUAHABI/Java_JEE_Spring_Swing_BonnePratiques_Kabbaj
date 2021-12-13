import java.util.*;

class Livre {

	private String titre;
	private String auteur;
	private String isbn;
	
	public Livre(String isbn, String titre){
		setIsbn(isbn);
		setTitre(titre);	
	}
	
	public Livre(String isbn, String titre, String auteur){
		this(isbn, titre);
		setAuteur(auteur);
	}
	
	public void setIsbn(String isbn){
		this.isbn = isbn;
	}
	
	public String getIsbn(){
		return this.isbn;
	}

	public void setTitre(String titre){
		this.titre = titre;
	}
	
	public String getTitre(){
		return this.titre;
	}
	public void setAuteur(String auteur){
		this.auteur= auteur;
	}
	
	public String getAuteur(){
		return this.auteur;
	}
	
	public boolean equals(Object o){
		if(o==null)
			return false;
		if(!(o instanceof Livre))
			return false;
		return this.getIsbn().equals(((Livre)o).getIsbn());
	}
	
	public int hashCode(){
		int hash = isbn.hashCode();
		hash += 31*hash + titre.hashCode();
		return hash;
	}
	
	public String toString(){
		return "le livre " + this.titre + " de l'auteur " + this.auteur + " possède l'identifiant " + this.isbn;
	}
	
}

// l.eqauls(k) == true  =>  l.hashCode()==k.hashCode()
// l.hashCode()!=k.hashCode() => l.eqauls(k) == false
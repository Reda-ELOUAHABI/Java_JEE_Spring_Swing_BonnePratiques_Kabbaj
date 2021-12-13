import java.util.*;
import java.util.stream.*;


class Catalogue{

	private List<Livre> livres = new ArrayList<>();
	
	public void add(Livre l){
		if(contains(l)==true) return;
		livres.add(l);
	}
	
	public Livre get(int i){
		return livres.get(i);
	}
	
	public Boolean remove(Livre l){
		return livres.remove(l);
	}
	
	public int size(){
		return livres.size();
	}

	public Boolean contains(Livre l){
		return livres.contains(l);
	}

/*	
	public Livre chercherLiver(String isbn){
		return livres.stream()
					 .filter(l -> l.getIsbn().equals(isbn))
					 .findAny().get();
	}
*/

	public List<Livre> chercherLiver(String mots){
		return livres.stream()
					 .filter(l -> l.getTitre().startsWith(mots))
					 .collect(Collectors.toList());
	}
	
	@Override
	public String toString(){
		String resultat =  "Catalogue est compose des livres suivants : \n";
		for(Livre l : livres)
			resultat += "\t\t" + l.toString() + "\n";
		return resultat;
	}
}
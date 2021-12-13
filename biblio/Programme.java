public class Programme{

	
	public static void main(String[] args){
		boolean resultat;
		Catalogue c = new Catalogue();
		System.out.println("test du constructeur Catalogue");
		resultat = c.size()==0;
		System.out.println("size resultat " + resultat);
		
		
		Livre l = new Livre ("1234", "Programmer en Java");
		c.add(l);
		resultat = c.size()==1;
		System.out.println("size resultat " + resultat);
		resultat = c.get(0).equals(l);
		System.out.println("get livre resultat " + resultat);
		
		resultat = c.remove(l);
		System.out.println("remove resultat " + resultat);
		resultat = c.size()==0;
		System.out.println("size resultat " + resultat);
				
		
		System.out.println("test du constructeur Livre");
		resultat = l.getIsbn().equals("1234");
		System.out.println("get isbn resultat " + resultat);
		resultat = l.getTitre().equals("Programme en Java");
		System.out.println("get titre resultat " + resultat);
		resultat = l.getAuteur()==null;
		System.out.println("get auteur resultat " + resultat);
		
		c.add(l);		
		
		l = new Livre ("1234", "Programmer en Java","Claud Delanoy");
		System.out.println("test du constructeur");
		resultat = l.getIsbn().equals("1234");
		System.out.println("get isbn resultat " + resultat);
		resultat = l.getTitre().equals("Programme en Java");
		System.out.println("get titre resultat " + resultat);
		resultat = l.getAuteur().equals("Claud Delanoy");
		System.out.println("get auteur resultat " + resultat);
		
		c.add(l);
		resultat = c.size()==1;
		System.out.println("size resultat " + resultat);
		resultat = c.get(0).equals(l);
		System.out.println("get livre resultat " + resultat);
		
		
		System.out.println(l);
		
		System.out.println(c);
		
//		System.out.println(c.get(0).equals(c.get(1)));
		l = new Livre ("2345", "Programmer en C","Claud Delanoy");
		c.add(l);
		l = new Livre ("3456", "Introduction aux bases de données");
		c.add(l);
		System.out.println();
		System.out.println("    chercher livre");
		System.out.println();
		
		System.out.println(c.chercherLiver("Programmer"));
	}


}
package ma.ac.emi.ginfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ma.ac.emi.ginfo.model.Professeur;
import ma.ac.emi.ginfo.repository.ProfesseurRepository;

@Controller
public class ProfesseurController {

	@Autowired
	private ProfesseurRepository prr;

	@RequestMapping(value = "/professeurs", method = RequestMethod.GET)
	public String index(Model model, 
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {

		Page<Professeur> pageprofesseurs = prr.findAll(PageRequest.of(page, size));
		model.addAttribute("listProfesseurs", pageprofesseurs.getContent());
		int[] pages = new int[pageprofesseurs.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("size", size);
		return "Professeurs";
	}

	@RequestMapping(value = "/chercherProfesseur")
	public String Chercher(Model model, 
			@RequestParam(name = "nomProfesseur", defaultValue = "") String nom,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		
		System.out.println(nom.length());
		if (nom.length()==0 && page==0 && size ==2) {
			return "redirect:/professeurs";
		}
		Page<Professeur> pageProfesseurs;
		if(nom.length()==0)
			pageProfesseurs = prr.findAll(PageRequest.of(page, size));
		else
			pageProfesseurs= prr.findByNom(nom, PageRequest.of(page, size));
		model.addAttribute("listProfesseurs", pageProfesseurs.getContent());
		int[] pages = new int[pageProfesseurs.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("size", size);
		model.addAttribute("nomProfesseur", nom);

		return "Professeurs";
	}
}

package ma.ac.emi.ginfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ma.ac.emi.ginfo.model.Matiere;
import ma.ac.emi.ginfo.repository.MatiereRepository;

@Controller
public class MatiereController {

	@Autowired
	private MatiereRepository mr;

	@RequestMapping(value = "/matieres", method = RequestMethod.GET)
	public String index(Model model, 
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {

		Page<Matiere> pagematieres = mr.findAll(PageRequest.of(page, size));
		System.out.println(pagematieres.getContent());
		model.addAttribute("listMatieres", pagematieres.getContent());
		int[] pages = new int[pagematieres.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("size", size);
		return "Matieres";
	}

	@RequestMapping(value = "/chercherMatiere")
	public String Chercher(Model model, 
			@RequestParam(name = "nomMatiere", defaultValue = "") String nom,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		
		System.out.println(nom.length());
		if (nom.length()==0 && page ==0 && size ==2) {
			return "redirect:/matieres";
		}

		Page<Matiere> pagematieres;
		if (nom.length()!=0 )
			pagematieres = mr.findByNom(nom, PageRequest.of(page, size));
		else
			pagematieres = mr.findAll(PageRequest.of(page, size));
		
		model.addAttribute("listMatieres", pagematieres.getContent());
		int[] pages = new int[pagematieres.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("size", size);
		model.addAttribute("nomMatiere", nom);

		return "Matieres";
	}
}

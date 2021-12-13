package ma.ac.emi.ginfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ma.ac.emi.ginfo.model.Student;
import ma.ac.emi.ginfo.repository.StudentRepository;

@Controller
public class StudentController {

	@Autowired
	private StudentRepository sr;

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String index(Model model, 
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {

		Page<Student> pagestudents = sr.findAll(PageRequest.of(page, size));
		System.out.println(pagestudents.getContent());
		model.addAttribute("listStudents", pagestudents.getContent());
		int[] pages = new int[pagestudents.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("size", size);
		return "Students";
	}

	@RequestMapping(value = "/chercherStudent")
	public String Chercher(Model model, 
			@RequestParam(name = "nomStudent", defaultValue = "") String nom,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		
		System.out.println(nom.length());
		if (nom.length()==0 && page ==0 && size ==2) {
			return "redirect:/students";
		}
		Page<Student> pagestudents;
		if(nom.length()==0)
			pagestudents = sr.findAll(PageRequest.of(page, size));
		else
			pagestudents = sr.findByNom(nom, PageRequest.of(page, size));
		model.addAttribute("listStudents", pagestudents.getContent());
		int[] pages = new int[pagestudents.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("size", size);
		model.addAttribute("nomStudent", nom);

		return "Students";
	}
}

package ma.ac.emi.ginfo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ma.ac.emi.ginfo.model.Matiere;
import ma.ac.emi.ginfo.model.Note;
import ma.ac.emi.ginfo.model.Student;
import ma.ac.emi.ginfo.repository.MatiereRepository;
import ma.ac.emi.ginfo.repository.NoteRepository;
import ma.ac.emi.ginfo.repository.StudentRepository;

@Controller
public class NoteController {

	@Autowired
	private NoteRepository nr;
	@Autowired
	private StudentRepository sr;
	@Autowired
	private MatiereRepository mr;

	@RequestMapping(value = "/notes", method = RequestMethod.GET)
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<Note> pagenotes = nr.findAll(PageRequest.of(page, size));
		System.out.println(pagenotes.getContent());
		model.addAttribute("listNotes", pagenotes.getContent());
		int[] pages = new int[pagenotes.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("size", size);
		return "Notes";
	}

	@RequestMapping(value = "/chercherNote")
	public String Chercher(Model model,
			@RequestParam(name = "matriculeStudent", defaultValue = "") String matriculeStudent,
			@RequestParam(name = "nomMatiere", defaultValue = "") String nomMatiere,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {

		Page<Note> pagenotes = null;
		Matiere m = null;
		Student s = null;
		Boolean error = true;
		System.out.println(matriculeStudent);

		if (nomMatiere.length() != 0)
			m = mr.findByNom(nomMatiere);
		if (matriculeStudent.length() != 0)
			s = sr.findById(Long.parseLong(matriculeStudent)).get();

		if (matriculeStudent.length() == 0 && nomMatiere.length() != 0) {
			if (m != null) {
				pagenotes = nr.findByIdMatiere(m, PageRequest.of(page, size));
				error = false;
			}
		}

		if (nomMatiere.length() == 0 && matriculeStudent.length() != 0) {
			if (s != null) {
				System.out.println(s);
				pagenotes = nr.findByIdStudent(s, PageRequest.of(page, size));
				error = false;
			}
		}

		if (matriculeStudent.length() == 0 && nomMatiere.length() == 0) {
			pagenotes = nr.findAll(PageRequest.of(page, size));
			error = false;
		}

		if (matriculeStudent.length() != 0 && nomMatiere.length() != 0)
			if (s != null && m != null) {
				pagenotes = nr.findByIdStudentAndIdMatiere(s, m, PageRequest.of(page, size));
				error = false;
			}

		if (error)
			return "redirect:/notes";

		model.addAttribute("listNotes", pagenotes.getContent());
		int[] pages = new int[pagenotes.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("size", size);
		model.addAttribute("matriculeStudent", matriculeStudent);
		model.addAttribute("nomMatiere", nomMatiere);

		return "Notes";
	}

	@RequestMapping(value = "/ajouternote", method = RequestMethod.GET)
	public String ajouterNote(Model model) {
		return "ajouternote";
	}

	@RequestMapping(value = "/ajouternote", method = RequestMethod.POST)
	public String ajouterNote(Model model, String matricule, String nomMatiere, float note, String abreciation) {
		Optional<Student> s = sr.findById(Long.parseLong(matricule));
		Matiere m = mr.findByNom(nomMatiere);
		if (s.isPresent() && m != null) {
			nr.save(new Note(note, abreciation, s.get(), m));
			return "redirect:/notes";
		}
		model.addAttribute("matricule", matricule);
		model.addAttribute("nomMatiere", nomMatiere);
		model.addAttribute("note", note);
		model.addAttribute("abreciation", abreciation);
		return "ajouternote";
	}
}

package ma.ac.emi.ginfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlleur {

	@GetMapping("/")
	public String index() {
		return "redirect:/notes";
	}
	
	@GetMapping("/login")
	public String autentification() {
		return "login";
	}
	
}

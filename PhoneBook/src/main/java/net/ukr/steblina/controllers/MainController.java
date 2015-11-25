package net.ukr.steblina.controllers;



import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.ukr.steblina.models.User;



@Controller
public class MainController {

	
	@RequestMapping(value = "/")
	public String index(Principal principal) {
			return "redirect:/user/"+principal.getName();
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	
}

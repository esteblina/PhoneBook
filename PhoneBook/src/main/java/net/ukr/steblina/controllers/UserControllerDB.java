package net.ukr.steblina.controllers;

import java.security.Principal;
import java.sql.SQLWarning;
import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.ukr.steblina.models.Phone;
import net.ukr.steblina.models.PhoneDAO;
import net.ukr.steblina.models.User;
import net.ukr.steblina.models.UserDAO;

@Controller
@Profile("db")
@RequestMapping(value = "/user")
public class UserControllerDB {

	

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private PhoneDAO phoneDAO;

	@RequestMapping(value = "/save")
	public String create(@Valid User user) {
		try {
			//if(user.validate())
			System.out.println(user);
			userDAO.save(user);
		} catch (ValidationException invalid) {
			System.out.println(invalid.getMessage());
		} catch (Exception ex) {
		}
		return "redirect:/login";
	}
	

	@RequestMapping(value = "/{login}")
	public String phones(@PathVariable String login, Principal principal, Model model) throws SQLWarning {
		if(!principal.getName().equals(login))
			return "redirect:/user/"+principal.getName();
		if(login==null||login.equals(""))
			return "redirect:/user/"+principal.getName();
		List<Phone> pl=phoneDAO.getAllByUser(userDAO.getByLogin(login));
		model.addAttribute("userName",principal.getName());
		model.addAttribute("phones",pl);
		model.addAttribute("newPhone", new Phone());
		return "index";
	}

	@RequestMapping(value = "/")
	public String no(Principal principal){

			return "redirect:/user/"+principal.getName();

	}

}

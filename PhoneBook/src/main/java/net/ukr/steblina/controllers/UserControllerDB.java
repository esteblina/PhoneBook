package net.ukr.steblina.controllers;

import java.security.Principal;
import java.sql.SQLWarning;
import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

/*	@RequestMapping(value = "/save")
	@ResponseBody
	public String create(String login, String pass, String fullname) {
		try {
			User user = new User();

			user.setLogin(login);
			user.setPassword(pass);
			user.setFullname(fullname);

			userDAO.save(user);
		} catch (ValidationException invalid) {
			return invalid.getMessage();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "User succesfully saved!";
	}*/
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
		List<Phone> pl=phoneDAO.getAllByUser(userDAO.getByLogin(login));
		model.addAttribute("phones",pl);
		model.addAttribute("newPhone", new Phone());
		return "index";
	}

/*	@RequestMapping(value = "/find")
	@ResponseBody
	public String getById(String id) {
		String login;
		try {
			User user = userDAO.getById(Integer.parseInt(id));
			login = user.getLogin();
		} catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + login;
	}

	@RequestMapping(value = "/users")
	@ResponseBody
	public String getUsers() {
		String userList = "";
		try {
			List<User> users = userDAO.getAll();
			for (User user : users)
				userList += user;
		} catch (Exception ex) {
			return "Users not found";
		}
		return userList;
	}*/

}

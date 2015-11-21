package net.ukr.steblina.controllers;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import net.ukr.steblina.models.User;
import net.ukr.steblina.models.UserDAO;

@Controller
@Profile("db")
@RequestMapping(value = "/user")
public class UserControllerDB {

	

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/save")
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
	}

	@RequestMapping(value = "/find")
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
	}

}

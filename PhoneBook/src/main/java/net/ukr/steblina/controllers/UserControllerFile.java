package net.ukr.steblina.controllers;

import java.io.File;
import java.security.Principal;
import java.sql.SQLWarning;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.ukr.steblina.models.Phone;
import net.ukr.steblina.models.User;
import net.ukr.steblina.models.UserPhonesDAO;
import net.ukr.steblina.models.UserPhonesDAOImpl;

@Controller
@Profile("file")
@RequestMapping(value = "/user")
public class UserControllerFile implements BasicController  {

	@Autowired
	private File file;
	@Autowired
	private UserPhonesDAO userPhonesDAO;// = new UserPhonesDAOImpl();

/*	@RequestMapping(value = "/save")
	@ResponseBody
	public String create(String login, String pass, String fullname) {
		try {
			User user = new User();

			user.setLogin(login);
			user.setPassword(pass);
			user.setFullname(fullname);
			userPhonesDAO.save(user,file);
		} catch (ValidationException invalid) {
			return invalid.getMessage();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "User succesfully saved!";
	}*/
	@RequestMapping(value = "/save")
	public String create(User user, Model model) {
		try {
			userPhonesDAO.save(user,file);
		} catch (ValidationException invalid) {
			model.addAttribute("error");//invalid.getMessage();
		} catch (Exception ex) {
			model.addAttribute("error");//ex.getMessage();
		}
		return "redirect:/login";
	}


	@RequestMapping(value = "/{login}")
	public String phones(@PathVariable String login, Principal principal, Model model) throws Exception {
		if(!principal.getName().equals(login))
			return "redirect:/user/"+principal.getName();
		if(login==null||login.equals(""))
			return "redirect:/user/"+principal.getName();

		model.addAttribute("userName",principal.getName());
		model.addAttribute("phones", userPhonesDAO.getByUser(userPhonesDAO.getByLogin(login, file),file).getPhones());
		model.addAttribute("newPhone", new Phone());
		return "index";
	}

	@RequestMapping(value = "/")
	public String redirect(Principal principal){

			return "redirect:/user/"+principal.getName();

	}
/*	@RequestMapping(value = "/find")
	@ResponseBody
	public String getById(String id) {
		String login;
		try {
			User user = userPhonesDAO.getById(Integer.parseInt(id),file);
			login = user.getLogin();
		} catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + login;
	}
*/
/*	@RequestMapping(value = "/users")
	@ResponseBody
	public String getUsers() {
		String userList = "";
		try {
			List<User> users = userPhonesDAO.getAllUsers(file);
			for (User user : users)
				userList += user;
		} catch (Exception ex) {
			return "Users not found";
		}
		return userList;
	}*/

}

package net.ukr.steblina.controllers;


import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.ukr.steblina.models.Phone;
import net.ukr.steblina.models.PhoneDAO;
import net.ukr.steblina.models.User;
import net.ukr.steblina.models.UserDAO;

@Controller
@Profile("db")
@RequestMapping(value = "/phone")
public class PhoneControllerDB {
	@Autowired
	private PhoneDAO phoneDAO;
	
	@Autowired
	private UserDAO userDAO;
	


/*	@RequestMapping(value = "/save")
	@ResponseBody
	public String create(String login, String lastname, String firstname,
						 String patronymic, String mobilephone, String homephone,
						 String address, String email) {
		try {
			User user = userDAO.getByLogin(login);
			Phone phone = new Phone();
			System.out.print(mobilephone);
			phone.setUser_id(user.getId());
			phone.setFirstname(firstname);
			phone.setLastname(lastname);
			phone.setPatronymic(patronymic);
			phone.setMobilephone(mobilephone);
			phone.setHomephone(homephone);
			phone.setAddress(address);
			phone.setEmail(email);
			
			phoneDAO.save(phone);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Phone succesfully saved!";
	}*/
	@RequestMapping(value = "/save")
	public String create(Phone phone) {
		try {
			phoneDAO.save(phone);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "index";
	}
	@RequestMapping(value = "/update")
	public String update(Phone phone, Principal principal) {
		try {
			phone.setUser_id(userDAO.getByLogin(principal.getName()).getId());
			phoneDAO.update(phone);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "index";
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String deleteById(String id) {
		try {
			Phone phone = phoneDAO.getById(Integer.parseInt(id));
			phoneDAO.delete(phone);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "The phone " + id +" deleted.";
	}
	
/*	@RequestMapping(value = "/update")
	@ResponseBody
	public String update(String id, String lastname, String firstname,
			 String patronymic, String mobilephone, String homephone,
			 String address, String email) {
		try {
			Phone phone = new Phone(phoneDAO.getById(Integer.parseInt(id)));
			
			phone.setId(Integer.parseInt(id));
			phone.setFirstname(firstname);
			phone.setLastname(lastname);
			phone.setPatronymic(patronymic);
			phone.setMobilephone(mobilephone);
			phone.setHomephone(homephone);
			phone.setAddress(address);
			phone.setEmail(email);
			
			phoneDAO.update(phone);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "The phone updated.";
	}*/

	@RequestMapping(value = "/find")
	@ResponseBody
	public String getPhone(Integer id) {
		Phone phone = null;
		Gson gson =new Gson();
		try {
			phone = phoneDAO.getById(id);
		} catch (Exception ex) {
			return null;
		}
		return gson.toJson(phone);
	}
}

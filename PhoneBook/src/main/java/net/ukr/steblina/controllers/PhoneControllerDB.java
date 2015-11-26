package net.ukr.steblina.controllers;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import net.ukr.steblina.models.Phone;
import net.ukr.steblina.models.PhoneDAO;
import net.ukr.steblina.models.UserDAO;

@Controller
@Profile("db")
@RequestMapping(value = "/phone")
public class PhoneControllerDB implements BasicController  {
	@Autowired
	private PhoneDAO phoneDAO;
	
	@Autowired
	private UserDAO userDAO;
	

	@RequestMapping(value = "/save")
	public String create(@Valid Phone newPhone, Principal principal, Model model) {
		try {
			newPhone.setUser_id(userDAO.getByLogin(principal.getName()).getId());
			phoneDAO.save(newPhone);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "redirect:/user/"+principal.getName();
	}
	@RequestMapping(value ={ "/update", "POST"})
	public String update(@Valid Phone newPhone, Principal principal) {
		try {
			phoneDAO.update(newPhone);
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "redirect:/user/"+principal.getName();
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteById(@PathVariable Integer id,Model model,Principal principal) {
		try {
			phoneDAO.delete(phoneDAO.getById(id));
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		return "redirect:/user/"+principal.getName();
	}
	
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

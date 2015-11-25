package net.ukr.steblina.controllers;


import java.io.File;
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
import net.ukr.steblina.models.User;
import net.ukr.steblina.models.UserPhones;
import net.ukr.steblina.models.UserPhonesDAO;
import net.ukr.steblina.models.UserPhonesDAOImpl;

@Controller
@Profile("file")
@RequestMapping(value = "/phone")
public class PhoneControllerFile {
	@Autowired
	private File file;
	@Autowired
	private UserPhonesDAO userPhonesDAO;// = new UserPhonesDAOImpl();
	
	@RequestMapping(value = "/save")
	public String create(@Valid Phone newPhone, Principal principal, Model model) {
		try {
			User user = userPhonesDAO.getByLogin(principal.getName(),file);
			userPhonesDAO.savePhone(newPhone,user,file);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return "redirect:/user/"+principal.getName();
	}
	@RequestMapping(value ={ "/update", "POST"})
	public String update(@Valid Phone newPhone, Principal principal) {
		try {
			User user = userPhonesDAO.getByLogin(principal.getName(),file);
			userPhonesDAO.updatePhone(newPhone, user, file);
	
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "redirect:/user/"+principal.getName();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteById(@PathVariable Integer id,Model model,Principal principal) {
		try {
			User user = userPhonesDAO.getByLogin(principal.getName(),file);
			userPhonesDAO.deletePhone(id,user,file);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		return "redirect:/user/"+principal.getName();
	}
	
	@RequestMapping(value = "/find")
	@ResponseBody
	public String getPhone(Integer id, Principal principal) {
		Phone phone = null;
		Gson gson =new Gson();
		try {
			User user = userPhonesDAO.getByLogin(principal.getName(),file);
			for(Phone p : userPhonesDAO.getByUser(user, file).getPhones())
				if(p.getId()==id)
					phone = p;
		} catch (Exception ex) {
			return null;
		}
		return gson.toJson(phone);
	}
/*
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
	
	@RequestMapping(value = "/update")
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
	}

	@RequestMapping(value = "/userphones")
	@ResponseBody
	public String getPhones(String login) {
		String phonesList = "";
		try {
			User user =userDAO.getByLogin(login);
			List<Phone> phones = phoneDAO.getAllByUser(user);
			for (Phone phone : phones)
				phonesList += phone;
		} catch (Exception ex) {
			return "Phones not found";
		}
		return phonesList;
	}*/
}

package net.ukr.steblina.controllers;


import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public String create(String login, String lastname, String firstname,
						 String patronymic, String mobilephone, String homephone,
						 String address, String email) {
		try {
			User user = userPhonesDAO.getByLogin(login,file);
			Phone phone = new Phone();

			phone.setUser_id(user.getId());
			phone.setFirstname(firstname);
			phone.setLastname(lastname);
			phone.setPatronymic(patronymic);
			phone.setMobilephone(mobilephone);
			phone.setHomephone(homephone);
			phone.setAddress(address);
			phone.setEmail(email);

			UserPhones userPhone = userPhonesDAO.getByUser(user,file);
			userPhone.addPhone(phone);

			userPhonesDAO.save(userPhone, file);

		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Phone succesfully saved!";
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

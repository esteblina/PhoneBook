package net.ukr.steblina.models;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

@Profile("file")
@Repository
public class UserPhonesDAOImpl implements UserPhonesDAO {

	@Override
	public void save(User user, File file) {

		List<UserPhones> userPhonesList = getSavedData(file);
		int size = userPhonesList.size();
		if(size==0)
			user.setId(0);
		else{
			for(UserPhones userPhones:userPhonesList){
				if(user.getLogin().equals(userPhones.getUser().getLogin()))
					throw new ValidationException("User already exist");
			}
			UserPhones lastUserPhones = userPhonesList.get(size-1);
			user.setId(lastUserPhones.getUser().getId()+1);
		}
		UserPhones userPhones = new UserPhones();
		userPhones.setUser(user);
		userPhonesList.add(userPhones);
		try{
			Writer writer = new FileWriter(file);
			Gson gson =  new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(userPhonesList, writer);
			writer.close();
		} catch (IOException e) {
			System.err.println("Write error");
		}

	}
	@Override
	public void savePhone(Phone phone, User user, File file) {

		List<UserPhones> userPhonesList = getSavedData(file);
		UserPhones userPhone=null;
			for(UserPhones userPhones:userPhonesList){
				if(user.getLogin().equals(userPhones.getUser().getLogin())){
					userPhone=userPhones;
					break;
				}
			}
		
		List<Phone> phones =userPhone.getPhones();
		if(phones.size()==0)
			phone.setId(0);
		else
			phone.setId(phones.size());
		userPhone.addPhone(phone);
		
		save(userPhone, file);

	}
	@Override
	public void updatePhone(Phone newPhone, User user, File file) {
		List<UserPhones> userPhonesList = getSavedData(file);
		UserPhones userPhone=null;
			for(UserPhones userPhones:userPhonesList){
				if(user.getLogin().equals(userPhones.getUser().getLogin())){
					userPhone=userPhones;
					break;
				}
			}
		

		userPhone.updatePhone(newPhone);
		
		save(userPhone, file);
	}
	
	@Override
	public void deletePhone(Integer id, User user, File file) {
		List<UserPhones> userPhonesList = getSavedData(file);
		UserPhones userPhone=null;
			for(UserPhones userPhones:userPhonesList){
				if(user.getLogin().equals(userPhones.getUser().getLogin())){
					userPhone=userPhones;
					break;
				}
			}
			Phone toDell=null;
		for(Phone p:userPhone.getPhones())
			if(p.getId()==id)
				toDell=p;
		

		userPhone.deletePhone(toDell);
		
		save(userPhone, file);
		
	}

	@Override
	public User getByLogin(String login, File file) throws Exception {
		List<UserPhones> userPhonesList = getSavedData(file);
		User user=null;
		int size = userPhonesList.size();
		if(size==0)
			throw new Exception("No users");
		else{
			for(UserPhones userPhones:userPhonesList){
				User tmpUser =userPhones.getUser();
				if(tmpUser.getLogin().equals(login))
					user=tmpUser;
			}
		}
		if(user==null)
			throw new Exception("No user");
		
		return user;
	}


	@Override
	public void save(UserPhones userPhone, File file) {
		List<UserPhones> userPhonesList = getSavedData(file);
		for(UserPhones userPhones: userPhonesList){
			if(userPhones.getUser().equals(userPhone.getUser())){
				
				userPhonesList.remove(userPhones);
				userPhonesList.add(userPhone);
				break;
			}
		}
		
	
		try{
			Writer writer = new FileWriter(file);
			Gson gson =  new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(userPhonesList, writer);
			writer.close();
		} catch (IOException e) {
			System.err.println("Write error");
		}
	}

	@Override
	public UserPhones getByUser(User user, File file) throws Exception {
		List<UserPhones> userPhonesList = getSavedData(file);
		UserPhones userPhones=null;
		for(UserPhones tmpUserPhones:userPhonesList){
			if(tmpUserPhones.getUser().equals(user))
				userPhones=tmpUserPhones;
		}
		if(userPhones==null)
			throw new Exception("No user");
		return userPhones;
	}

	public List<UserPhones> getSavedData(File file) {
		Type type = new TypeToken<List<UserPhones>>() {}.getType();
		Gson gson = new Gson();
		JsonReader reader=null;
		List<UserPhones> data=null;
		try {
			reader = new JsonReader(new FileReader(file));
			data= gson.fromJson(reader, type);
			reader.close(); 
		} catch (IOException e) {
			System.err.println("Read file problem");
		}
		if(data==null)
			data=new LinkedList<UserPhones>();
		return data;
	}

}

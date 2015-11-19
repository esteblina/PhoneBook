package net.ukr.steblina.models;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Profile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

@Profile("file")
public class UserPhonesDAOImpl implements UserPhonesDAO {

	@Override
	public void save(User user, File file) {

		List<UserPhones> userPhonesList = getSavedData(file);
		int size = userPhonesList.size();
		if(size==0)
			user.setId(0);
		else{
			UserPhones lastUserPhones = userPhonesList.get(size-1);
			user.setId(lastUserPhones.getUser().getId()+1);
		}
		UserPhones userPhones = new UserPhones();
		userPhones.setUser(user);
		userPhonesList.add(userPhones);
		try{
			Writer writer = new FileWriter(file);
			Gson gson =  new GsonBuilder().create();
			gson.toJson(userPhonesList, writer);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

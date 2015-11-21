package net.ukr.steblina.models;

import java.io.File;
import java.util.List;

public interface UserPhonesDAO {

	public void save(User user, File file);

	public User getById(Integer id, File file) throws Exception;
	
	public User getByLogin(String login, File file) throws Exception;

	public List<User> getAllUsers(File file) throws Exception;

	public void save(UserPhones userPhone, File file);

	public UserPhones getByUser(User user, File file) throws Exception;

}

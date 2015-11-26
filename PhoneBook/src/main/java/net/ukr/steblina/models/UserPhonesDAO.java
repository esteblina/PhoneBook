package net.ukr.steblina.models;

import java.io.File;

public interface UserPhonesDAO {

	public void save(User user, File file);

	
	public User getByLogin(String login, File file) throws Exception;


	public void save(UserPhones userPhone, File file);

	public UserPhones getByUser(User user, File file) throws Exception;

	void savePhone(Phone phone, User user, File file);

	public void updatePhone(Phone newPhone, User user, File file);

	public void deletePhone(Integer id, User user, File file);

}

package net.ukr.steblina.models;

import java.sql.SQLWarning;
import java.util.List;

public interface UserDAO {
	public void save(User user);
/*	public void delete(User user);
	public List<User> getAll();*/
/*	public User getById(Integer id);*/
/*	public void update(User user);*/
	public User getByLogin(String login) throws SQLWarning;
}

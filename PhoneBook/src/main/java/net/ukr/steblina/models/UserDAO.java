package net.ukr.steblina.models;

import java.sql.SQLWarning;

public interface UserDAO {
	public void save(User user);
	public User getByLogin(String login) throws SQLWarning;
}

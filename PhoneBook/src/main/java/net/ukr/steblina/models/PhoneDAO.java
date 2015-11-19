package net.ukr.steblina.models;

import java.util.List;

public interface PhoneDAO {
	public void save(Phone phone);
	public void delete(Phone phone);
	public void update(Phone phone);
	public List<Phone> getAll();
	public Phone getById(Integer id);
	public List<Phone> getAllByUser(User user);
}

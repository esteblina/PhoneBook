package net.ukr.steblina.models;

import java.util.List;

public class UserPhones {
	
	private User user;
	private List<Phone> phones;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
	public void addPhone(Phone phone){
		phones.add(phone);
	}
	
	public void deletePhone(Phone phone){
		phones.remove(phone);
	}
	
	public void updatePhone(Phone phoneNew){
		for(Phone phone: phones){
			if(phone.getId()==phoneNew.getId()){
				phones.remove(phone);
				phones.add(phoneNew);
				break;
			}
		}
	}

}

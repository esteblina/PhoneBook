package net.ukr.steblina.models;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.ukr.steblina.check.Validation;

@Entity
@Table(name = "phones")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;


	@Column(name = "user_id")
	private Integer user_id;

	@NotNull
	@Column(name = "lastname")
	@Size(min = 4)
	private String lastname;

	@NotNull
	@Column(name = "firstname")
	@Size(min = 4)
	private String firstname;

	@NotNull
	@Column(name = "patronymic")
	@Size(min = 4)
	private String patronymic;

	@NotNull
	@Column(name = "mobilephone")
	@Size(min = 13)
	private String mobilephone;

	@Column(name = "homephone")
	@Size(min = 13)
	private String homephone;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	public Phone(){
	}

	public Phone(Phone phone) {
		this.id = phone.getId();
		this.user_id =  phone.getUser_id();
		this.lastname =  phone.getLastname();
		this.firstname =  phone.getFirstname();
		this.patronymic =  phone.getPatronymic();
		this.mobilephone =  phone.getMobilephone();
		this.homephone =  phone.getHomephone();
		this.address =  phone.getAddress();
		this.email =  phone.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) throws ValidationException {
		if(lastname.length()<4)
			throw new ValidationException("Min Last Name lenght 4");
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		if(firstname.length()<4)
			throw new ValidationException("Min First Name lenght 4");
		this.firstname = firstname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		if(patronymic.length()<4)
			throw new ValidationException("Min Patronymic lenght 4");
		this.patronymic = patronymic;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		if(!Validation.phoneValid(mobilephone))
			throw new ValidationException("Mobilephone invalid");
		this.mobilephone = mobilephone;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		if(!Validation.phoneValid(homephone))
			throw new ValidationException("Homephone invalid");
		this.homephone = homephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(!email.equals(""))
		if(!Validation.emailValid(email))
			throw new ValidationException("Email invalid");
		this.email = email;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", user_id=" + user_id + ", lastname=" + lastname + ", firstname=" + firstname
				+ ", patronymic=" + patronymic + ", mobilephone=" + mobilephone + ", homephone=" + homephone
				+ ", address=" + address + ", email=" + email + "]";
	}
	
}

package net.ukr.steblina.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "phones")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
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
		this.id = phone.id;
		this.user_id =  phone.user_id;
		this.lastname =  phone.lastname;
		this.firstname =  phone.firstname;
		this.patronymic =  phone.patronymic;
		this.mobilephone =  phone.mobilephone;
		this.homephone =  phone.homephone;
		this.address =  phone.address;
		this.email =  phone.email;
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

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
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
		this.email = email;
	}

	@Override
	public String toString() {
		return firstname + "   " + lastname + "    " + patronymic + "     " + mobilephone + "      " + homephone
				+ "    " + address + "    " + email;
	}

}

package net.ukr.steblina.models;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.DigestUtils;

import net.ukr.steblina.check.Validation;


@Entity
@Table(name = "users")
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Column(name="login", unique = true)
	@Size(min=3, max=50)
	private String login;

	@NotNull
	@Column(name="password")
	@Size(min=5, max=50)
	private String password;

	@NotNull
	@Column(name="fullname")
	@Size(min=5, max=50)
	private String fullname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws ValidationException {
		if(!Validation.loginValid(login))
			throw new ValidationException("Login invalid");
		this.login = login.toLowerCase();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(!Validation.passwordValid(password))
			throw new ValidationException("Password invalid");
		this.password = DigestUtils.md5DigestAsHex(password.getBytes());
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		if(fullname.length()<5)
			throw new ValidationException("Full Name have small lenght");
		this.fullname = fullname;
	}

	public User() {
	}

	@Override
	public String toString(){
		return id+"    "+login+"    "+password+"      "+fullname;
	}
	
}

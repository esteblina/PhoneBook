package net.ukr.steblina.check;

import java.util.regex.Pattern;

public class Validation {
	
	private static final Pattern loginPatern = Pattern.compile("^[A-Za-z]{3,}$");
	private static final Pattern passwordPatern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,})");
	
	private static final Pattern emailPatern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+"+
															   "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	public static boolean loginValid(String login){
		return loginPatern.matcher(login).matches();
	}
	
	public static boolean passwordValid(String pass){
		return passwordPatern.matcher(pass).matches();
	}
	
	public static boolean emailValid(String email){
		return emailPatern.matcher(email).matches();
	}
}

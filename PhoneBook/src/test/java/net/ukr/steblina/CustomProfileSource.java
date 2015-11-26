package net.ukr.steblina;

import org.springframework.test.annotation.ProfileValueSource;



public class CustomProfileSource implements ProfileValueSource {

  
	@Override
	public String get(String arg0) {
		return (arg0.equals("profile") ? "db" : null);
	}

}

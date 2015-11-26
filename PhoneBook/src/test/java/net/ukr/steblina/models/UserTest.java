package net.ukr.steblina.models;

import javax.validation.ValidationException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.DigestUtils;

public class UserTest {
	
	User user;
	String login="login",
			errlogin="lo1",
			password="Pass1",
			errpasword="password",
			fullname="Full name";

	@Before
	public void setUp() throws Exception {
		user = new User();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetLogin() {
		user.setLogin(login);
		Assert.assertEquals(login, user.getLogin());
		Exception exception=null;
		try{
			user.setLogin(errlogin);
		}catch(Exception e){
			exception=e;
		}
		Assert.assertNotNull(exception);
		Assert.assertEquals("", ValidationException.class, exception.getClass());
	}

	@Test
	public void testSetPassword() {
		user.setPassword(password);
		Assert.assertEquals(DigestUtils.md5DigestAsHex(password.getBytes()), user.getPassword());
		Exception exception=null;
		try{
			user.setPassword(errpasword);
		}catch(Exception e){
			exception=e;
		}
		Assert.assertNotNull(exception);
		Assert.assertEquals("", ValidationException.class, exception.getClass());
	}

	@Test
	public void testSetFullname() {
		user.setFullname(fullname);
		Assert.assertEquals(fullname, user.getFullname());
	}

}

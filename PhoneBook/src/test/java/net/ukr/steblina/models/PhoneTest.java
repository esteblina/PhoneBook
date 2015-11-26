package net.ukr.steblina.models;

import javax.validation.ValidationException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PhoneTest {

	Phone phone;
	String lastname = "lastname", firstname = "firsname", patronymic = "patronymic", phonenumber1 = "+380505762430",
			phonenumber2 = "+380(50)576-24-30", phonenumber3 = "+38050576-24-30", email = "e.steblina@ukr.net";

	@Before
	public void setUp() throws Exception {
		phone = new Phone();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetLastname() {
		phone.setLastname(lastname);
		Assert.assertEquals(lastname, phone.getLastname());
		Exception exception = null;
		try {
			phone.setLastname("111");
		} catch (Exception e) {
			exception = e;
		}
		Assert.assertNotNull(exception);
		Assert.assertEquals("", ValidationException.class, exception.getClass());
	}

	@Test
	public void testSetFirstname() {
		phone.setFirstname(firstname);
		Assert.assertEquals(firstname, phone.getFirstname());
		Exception exception = null;
		try {
			phone.setFirstname("111");
		} catch (Exception e) {
			exception = e;
		}
		Assert.assertNotNull(exception);
		Assert.assertEquals("", ValidationException.class, exception.getClass());
	}

	@Test
	public void testSetPatronymic() {
		phone.setPatronymic(patronymic);
		Assert.assertEquals(patronymic, phone.getPatronymic());
		Exception exception = null;
		try {
			phone.setPatronymic("111");
		} catch (Exception e) {
			exception = e;
		}
		Assert.assertNotNull(exception);
		Assert.assertEquals("", ValidationException.class, exception.getClass());
	}

	@Test
	public void testSetMobilephone() {
		Exception exception = null;
		try {
			phone.setMobilephone(phonenumber1);
			phone.setMobilephone(phonenumber2);
			phone.setMobilephone(phonenumber3);
		} catch (Exception e) {
			exception = e;
		}
		Assert.assertNull(exception);
		try {
			phone.setMobilephone("22234");
		} catch (Exception e) {
			exception = e;
		}
		Assert.assertNotNull(exception);
		Assert.assertEquals("", ValidationException.class, exception.getClass());
	}

	@Test
	public void testSetHomephone() {
		Exception exception = null;
		try {
			phone.setHomephone(phonenumber1);
			phone.setHomephone(phonenumber2);
			phone.setHomephone(phonenumber3);
		} catch (Exception e) {
			exception = e;
		}
		Assert.assertNull(exception);
		try {
			phone.setHomephone("9922");
		} catch (Exception e) {
			exception = e;
		}
		Assert.assertNotNull(exception);
		Assert.assertEquals("", ValidationException.class, exception.getClass());
	}

	@Test
	public void testSetEmail() {

		phone.setEmail(email);
		Assert.assertEquals(email, phone.getEmail());
		Exception exception = null;
		try {
			phone.setEmail("@9922234");
		} catch (Exception e) {
			exception = e;
		}
		Assert.assertNotNull(exception);
		Assert.assertEquals("", ValidationException.class, exception.getClass());
	}

}

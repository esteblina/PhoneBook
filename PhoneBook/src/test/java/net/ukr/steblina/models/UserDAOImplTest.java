package net.ukr.steblina.models;


import java.sql.SQLWarning;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.steblina.AbstractPhoneBookApplicationTests;

@Transactional
public class UserDAOImplTest extends AbstractPhoneBookApplicationTests {
	
	@Autowired
	private UserDAO userDAO;

	private User user1;
	
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		user1=new User();
		user1.setLogin("login");
		user1.setPassword("Pass1");
		user1.setFullname("login1 Pass1");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() throws SQLWarning {
		userDAO.save(user1);
		User saved=userDAO.getByLogin(user1.getLogin());
		
		Assert.assertNotNull("Saved user", saved);
		Assert.assertEquals("Login", user1.getLogin(), saved.getLogin());
	}

	@Test
	public void testGetByLogin() throws SQLWarning {
		userDAO.save(user1);
		User saved=userDAO.getByLogin(user1.getLogin());
		
		Assert.assertNotNull("Saved user", saved);
		Assert.assertEquals("Passwords", user1.getPassword(), saved.getPassword());
		Assert.assertEquals("Full name", user1.getFullname(), saved.getFullname());
	}

}

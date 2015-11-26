package net.ukr.steblina.models;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.steblina.AbstractPhoneBookApplicationTests;



@Transactional
@IfProfileValue(name="profile",value="db")
public class PhoneDAOImplTest extends AbstractPhoneBookApplicationTests {
	
	@Autowired
	private PhoneDAO phoneDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	User user1;
	Phone phone,phone2;
	final Integer id=99;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		user1=new User();
		user1.setLogin("login");
		user1.setPassword("Pass1");
		user1.setFullname("login1 Pass1");
		userDAO.save(user1);
		user1=userDAO.getByLogin(user1.getLogin());
		phone=new Phone();
		phone.setFirstname("firstname");
		phone.setLastname("lastname");
		phone.setPatronymic("patronymic");
		phone.setUser_id(user1.getId());
		phone.setMobilephone("+380(66)576-24-30");
		phone.setHomephone("+380665762430");
		phone2=new Phone(phone);
		phone2.setFirstname("firstname2");
		
		phone2.setId(id);
		phoneDAO.save(phone2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		phoneDAO.save(phone);
		Phone saved = phoneDAO.getAllByUser(user1).get(1);
		
		Assert.assertEquals(phone, saved);
	}

	@Test
	public void testDelete() {
		phoneDAO.delete(phone2);
		
		Phone deleted= phoneDAO.getById(id);
		Assert.assertNull(deleted);
	}

	@Test
	public void testUpdate() {
		phone2.setAddress("address");
		phoneDAO.update(phone2);
		Phone updated = phoneDAO.getById(phone2.getId());
		
		Assert.assertEquals("address", updated.getAddress());
	}

	@Test
	public void testGetById() {
		Phone byId = phoneDAO.getById(phone2.getId());
		Assert.assertEquals(phone2, byId);
	}

	@Test
	public void testGetAllByUser() {
		int size1=phoneDAO.getAllByUser(user1).size();
		phoneDAO.save(phone);
		int size2=phoneDAO.getAllByUser(user1).size();
		Assert.assertEquals(1, size1);
		Assert.assertEquals(2, size2);
		
	}

}

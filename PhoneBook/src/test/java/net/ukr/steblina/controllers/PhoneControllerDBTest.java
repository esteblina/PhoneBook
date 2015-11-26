package net.ukr.steblina.controllers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.steblina.AbstractPhoneBookApplicationTests;
import net.ukr.steblina.models.Phone;
import net.ukr.steblina.models.PhoneDAO;
import net.ukr.steblina.models.UserDAO;

@Transactional
public class PhoneControllerDBTest extends AbstractPhoneBookApplicationTests {

	
	@Mock
	private UserDAO userDAO;
	@Mock
	private PhoneDAO phoneDAO;
	@InjectMocks
	private PhoneControllerDB controller;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		setUp(controller);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreate() throws Exception {

		phoneDAO.save(any(Phone.class));

       verify(phoneDAO, times(1)).save(any(Phone.class));



	}

	@Test
	public void testGetPhone() throws Exception {

        Integer id = new Integer(1);
        Phone phone = new Phone();

        when(phoneDAO.getById(id)).thenReturn(phone);

        String uri = "/phone/find?id={id}";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
     
        verify(phoneDAO, times(1)).getById(id);

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);

	}


}

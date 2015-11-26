package net.ukr.steblina;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import net.ukr.steblina.controllers.BasicController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhoneBookApplication.class)
@TestPropertySource(properties = "lardi.conf=E:/hellish/wp/application.properties")
@ProfileValueSourceConfiguration(CustomProfileSource.class)
@WebAppConfiguration
public abstract class AbstractPhoneBookApplicationTests {
	protected MockMvc mvc;


    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected void setUp() throws Exception {
    	
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected void setUp(BasicController controller) {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    protected String mapToJson(Object obj) {
    	Gson gson =new Gson();
        return gson.toJson(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz){
    	Gson gson =new Gson();
        return gson.fromJson(json, clazz);
    }
}

package springbook.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.dao.AppContext;
import springbook.dao.AppContext.TestAppContext;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="/test-applicationContext.xml")
@ContextConfiguration(classes={AppContext.class, TestAppContext.class})
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@Test
	public void bean(){
		assertThat(this.userService, is(notNullValue()));
	}

}

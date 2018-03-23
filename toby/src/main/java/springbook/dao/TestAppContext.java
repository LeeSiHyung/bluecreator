package springbook.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

import springbook.service.DummyMailSender;
import springbook.service.UserService;
import springbook.service.UserServiceImpl.TestUserService;


@Configuration
public class TestAppContext {
	
	@Autowired
	UserDao userDao;
	
	// @Bean
	// public UserService testUserService(){
	// 	TestUserService testService = new TestUserService();
	// 	//testService.setUserDao(userDao());
	// 	testService.setUserDao(userDao);
	// 	testService.setMailSender(mailSender());
	// 	return testService;
	// }
	
	@Bean
	public UserService testUserService(){
		return new TestUserService();
	}
	
	@Bean
	public MailSender mailSender(){
		return new DummyMailSender();
	}

}

package springbook.dao;

import java.sql.Driver;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springbook.service.DummyMailSender;
import springbook.service.UserService;
import springbook.service.UserServiceImpl.TestUserService;

@Configuration
// @ImportResource 애노테이션을 이용하면 DI 설정정보에서 XML의 설정정보를 가져오게 만들 수 있다.
// @ImportResource("/test-applicationContext.xml")
@EnableTransactionManagement
@ComponentScan(basePackages = "springbook")
// SqlServiceContext는 AppContext에 포함되는 보조 설정정보로 사용
// 프로파일을 적용하면 모든 설정 클래스를 부담 없이 메인 설정 클래스에서 @Import해도 된다는 장점이 있다.
//@Import({ SqlServiceContext.class, TestAppContext.class,ProductionAppContext.class })
@Import({ SqlServiceContext.class}) // 중첩 멤버클래스로 지정하면 TestAppContext, ProductionAppContext 삭제 가능하다

@PropertySource("/database.properties")
public class AppContext {

	// @Autowired
	// SqlService sqlService;

	// @Resource
	// EmbeddedDatabase embeddedDatabase;

	@Autowired
	UserDao userDao;
	
	@Resource
	Environment env; // @Autowired가 안되는 현상은 확인 필요.
	
	@Value("${db.driverClass]")
	Class<? extends Driver> driverClass;
	
	@Value("${db.url}")
	String url;
	
	@Value("${db.username}")
	String username;
	
	@Value("${db.password}")
	String password;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		// dataSource.setDriverClass(Driver.class);
		// dataSource.setUrl("jdbc:mariadb://ec2-52-78-240-50.ap-northeast-2.compute.amazonaws.com:3306/toby?characterEncoding=UTF-8");
		// dataSource.setDriverClass(OracleDriver.class);
		// dataSource.setUrl("jdbc:oracle:thin:@211.237.24.135:1521:OTTR");
		
		// dataSource.setUsername("toby");
		// dataSource.setPassword("123qwe!@");
		
		
		try {
			dataSource.setDriverClass((Class<? extends java.sql.Driver>) Class.forName(env.getProperty("db.driverClass")));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		
		System.out.println("url = " + url);
		// dataSource.setDriverClass(driverClass);
		// dataSource.setUrl(url);
		// dataSource.setUsername(username);
		// dataSource.setPassword(password);
		
		
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}

	// @Bean
	// public UserDao userDao(){
	// //UserDaoJdbc dao = new UserDaoJdbc();
	// //dao.setDataSource(dataSource());
	// //dao.setSqlService(this.sqlService);
	// //return dao;
	// return new UserDaoJdbc();
	// }

	// @Bean
	// public UserService userService(){
	// UserServiceImpl service = new UserServiceImpl();
	// //service.setUserDao(userDao());
	// service.setUserDao(userDao);
	// service.setMailSender(mailSender());
	// return service;
	// }

	// @Bean
	// public UserService testUserService(){
	// TestUserService testService = new TestUserService();
	// //testService.setUserDao(userDao());
	// testService.setUserDao(userDao);
	// testService.setMailSender(mailSender());
	// return testService;
	// }
	//
	// @Bean
	// public MailSender mailSender(){
	// return new DummyMailSender();
	// }

	// @Bean
	// public SqlService sqlService(){
	// OxmSqlService sqlService = new OxmSqlService();
	// sqlService.setUnmarshaller(unmarshaller());
	// sqlService.setSqlRegistry(sqlRegistry());
	// return sqlService;
	// }
	//
	// @Bean
	// public SqlRegistry sqlRegistry(){
	// EmbeddedDbSqlRegistry sqlRegistry = new EmbeddedDbSqlRegistry();
	// //sqlRegistry.setDataSource(embeddedDatabase);
	// sqlRegistry.setDataSource(embeddedDatabase());
	// return sqlRegistry;
	// }
	//
	// @Bean
	// public Unmarshaller unmarshaller(){
	// Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	// marshaller.setContextPath("springbook.jaxb");
	// return marshaller;
	// }
	//
	// @Bean
	// public DataSource embeddedDatabase(){
	// return new EmbeddedDatabaseBuilder()
	// .setName("embeddedDatabase")
	// .setType(HSQL)
	// .addScript("classpath:springbook/service/sql/schema.sql")
	// .build();
	// }

	@Configuration
	@Profile("test")
	public static class TestAppContext {

		@Autowired
		UserDao userDao;

		// @Bean
		// public UserService testUserService(){
		// TestUserService testService = new TestUserService();
		// //testService.setUserDao(userDao());
		// testService.setUserDao(userDao);
		// testService.setMailSender(mailSender());
		// return testService;
		// }

		@Bean
		public UserService testUserService() {
			return new TestUserService();
		}

		@Bean
		public MailSender mailSender() {
			return new DummyMailSender();
		}

	}

	@Configuration
	@Profile("production")
	public static class ProductionAppContext {

		@Bean
		public MailSender mailSender() {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost("localhost");
			return mailSender;
		}

	}

}

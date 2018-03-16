package springbook.dao;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import javax.sql.DataSource;

import org.mariadb.jdbc.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.mail.MailSender;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springbook.service.DummyMailSender;
import springbook.service.UserService;
import springbook.service.UserServiceImpl;
import springbook.service.UserServiceImpl.TestUserService;
import springbook.service.sql.EmbeddedDbSqlRegistry;
import springbook.service.sql.OxmSqlService;
import springbook.service.sql.SqlRegistry;
import springbook.service.sql.SqlService;


@Configuration
// @ImportResource 애노테이션을 이용하면 DI 설정정보에서 XML의 설정정보를 가져오게 만들 수 있다.
// @ImportResource("/test-applicationContext.xml")
@EnableTransactionManagement
public class TestApplicationContext {
	
	@Autowired
	SqlService sqlService;
	
	//@Resource
	//EmbeddedDatabase embeddedDatabase;
	
	@Bean
	public DataSource dataSource(){
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(Driver.class);
		dataSource.setUrl("jdbc:mariadb://ec2-52-78-240-50.ap-northeast-2.compute.amazonaws.com:3306/toby?characterEncoding=UTF-8");
		dataSource.setUsername("toby");
		dataSource.setPassword("123qwe!@");
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(){
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
	@Bean
	public UserDao userDao(){
		UserDaoJdbc dao = new UserDaoJdbc();
		//dao.setDataSource(dataSource());
		dao.setSqlService(this.sqlService);
		return dao;
	}
	
	@Bean
	public UserService userService(){
		UserServiceImpl service = new UserServiceImpl();
		service.setUserDao(userDao());
		service.setMailSender(mailSender());
		return service;
	}
	
	@Bean
	public UserService testUserService(){
		TestUserService testService = new TestUserService();
		testService.setUserDao(userDao());
		testService.setMailSender(mailSender());
		return testService;
	}
	
	@Bean
	public MailSender mailSender(){
		return new DummyMailSender();
	}
	
	@Bean
	public SqlService sqlService(){
		OxmSqlService sqlService = new OxmSqlService();
		sqlService.setUnmarshaller(unmarshaller());
		sqlService.setSqlRegistry(sqlRegistry());
		return sqlService;
	}
	
	@Bean
	public SqlRegistry sqlRegistry(){
		EmbeddedDbSqlRegistry sqlRegistry = new EmbeddedDbSqlRegistry();
		//sqlRegistry.setDataSource(embeddedDatabase);
		sqlRegistry.setDataSource(embeddedDatabase());
		return sqlRegistry;
	}
	
	@Bean
	public Unmarshaller unmarshaller(){
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("springbook.jaxb");
		return marshaller;
	}
	
	@Bean
	public DataSource embeddedDatabase(){
		return new EmbeddedDatabaseBuilder()
		.setName("embeddedDatabase")
		.setType(HSQL)
		.addScript("classpath:springbook/service/sql/schema.sql")
		.build();
	}
	

}

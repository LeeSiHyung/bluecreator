package springbook.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.domain.Level;
import springbook.domain.User;
import springbook.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationContext.xml")
@DirtiesContext
public class UserDaoTest {
	@Autowired
	ApplicationContext context;
	
	private UserDao dao; 
	
	@Autowired
	DataSource dataSource;
	
	private User user1;
	private User user2;
	private User user3;
	List<User> users;
	
	@Autowired
	UserService userService;
	
	@Before
	public void setUp() {
		this.dao = this.context.getBean("userDao", UserDaoJdbc.class);
		
		this.user1 = new User("gyumee", "이시형", "springno1", Level.BASIC, 1, 0);
		this.user2 = new User("leegw700", "류기연", "springno2", Level.SILVER, 55, 10);
		this.user3 = new User("bumjin", "이진운", "springno3", Level.GOLD, 100, 40);
		
		users = Arrays.asList(
				new User("bumjin", "박범진", "p1", Level.BASIC, 49, 0),
				new User("joytouch", "강명성", "p2", Level.BASIC, 50, 0),
				new User("erwins", "신승한", "p3", Level.SILVER, 60, 29),
				new User("madnite1", "이상호", "p4", Level.SILVER, 60, 30),
				new User("green", "오민규", "p5", Level.GOLD, 100, 100)
				);
	}
	

	// User 오브젝트의 내용을 비교하는 검증 코드, 테스트내에서 반복적으로 사용되므로 분리
	private void checkSameUser(User user1, User user2) {
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
		assertThat(user1.getLevel(), is(user2.getLevel()));
		assertThat(user1.getLogin(), is(user2.getLogin()));
		assertThat(user1.getRecommend(), is(user2.getRecommend()));
	}
	
	private void checkLevel(User user, Level expectedLevel){
		User userUpdate = dao.get(user.getId());
		assertThat(userUpdate.getLevel(), is(expectedLevel));
	}
	
	@Test 
	public void andAndGet(){		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		User userget1 = dao.get(user1.getId());
		checkSameUser(userget1, user1);
		
		User userget2 = dao.get(user2.getId());
		checkSameUser(userget2, user2);
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure(){
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}

	
	@Test
	public void count(){
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
				
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
	
	@Test
	public void getAll(){
		dao.deleteAll();
		
		List<User> users0 = dao.getAll();
		assertThat(users0.size(), is(0));
		
		dao.add(user1);
		List<User> users1 = dao.getAll();
		assertThat(users1.size(), is(1));
		checkSameUser(user1, users1.get(0));
		
		dao.add(user2);
		List<User> users2 = dao.getAll();
		assertThat(users2.size(), is(2));
		checkSameUser(user1, users2.get(0));
		checkSameUser(user2, users2.get(1));
		
		dao.add(user3);
		List<User> users3 = dao.getAll();
		assertThat(users3.size(), is(3));
		checkSameUser(user3, users3.get(0));
		checkSameUser(user1, users3.get(1));
		checkSameUser(user2, users3.get(2));
	}
	
	//@Test(expected=DataAccessException.class)
	@Test(expected=DuplicateKeyException.class)
	public void duplicateKey(){
		dao.deleteAll();
		dao.add(user1);
		dao.add(user1);
	}
	
	// DuplicateKeyException의 rootCause가 SQLException이 아니라 MariaDB QueryException이 발생되는 이유에 대해서 확인이 필요함.
	
	// @Test
	// public void sqlExceptionTranslate(){
	// 	dao.deleteAll();
	// 	
	// 	try{
	// 		dao.add(user1);
	// 		dao.add(user1);
	// 	}catch(DuplicateKeyException ex){
	// 	/** rootCause를 가져와 형변환 한다. **/
	// 		SQLException sqlEx = (SQLException) ex.getRootCause(); // 
	// 		SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
	// 		assertThat(set.translate(null, null, sqlEx), is(DuplicateKeyException.class));
	// 	}
	// 	
	// }
	
	@Test
	public void update(){
		dao.deleteAll();
		
		dao.add(user1);
		dao.add(user2);
		
		user1.setName("오민규");
		user1.setPassword("springno6");
		user1.setLevel(Level.GOLD);
		user1.setLogin(1000);
		user1.setRecommend(999);
		dao.update(user1);
		
		User user1update = dao.get(user1.getId());
		checkSameUser(user1, user1update);
		
		User user2same = dao.get(user2.getId());
		checkSameUser(user2, user2same);
	}
	
	@Test
	public void upgradeLevels(){
		dao.deleteAll();
		for(User user : users){
			dao.add(user);
		}
		
		userService.upgradeLevels();
		
		checkLevel(users.get(0), Level.BASIC);
		checkLevel(users.get(1), Level.SILVER);
		checkLevel(users.get(2), Level.SILVER);
		checkLevel(users.get(3), Level.GOLD);
		checkLevel(users.get(4), Level.GOLD);
	}
	
	@Test
	public void add(){
		dao.deleteAll();
		
		User userWithLevel = users.get(4);
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);
		
		userService.add(userWithLevel);
		userService.add(userWithoutLevel);
		
		User userWithLevelRead = dao.get(userWithLevel.getId());
		User userWithoutLevelRead = dao.get(userWithoutLevel.getId());
		
		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
		assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
	}

}

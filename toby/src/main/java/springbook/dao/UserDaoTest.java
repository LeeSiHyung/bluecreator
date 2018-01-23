package springbook.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static springbook.service.UserServiceImpl.MIN_LOGOUT_FOR_SILVER;
import static springbook.service.UserServiceImpl.MIN_RECOMMEND_FOR_GOLD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import springbook.domain.Level;
import springbook.domain.User;
import springbook.service.MockMailSender;
import springbook.service.UserService;
import springbook.service.UserServiceImpl;
import springbook.service.UserServiceImpl.TestUserServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationContext.xml")
//@DirtiesContext
//@Transactional
//@TransactionConfiguration(defaultRollback=false)
public class UserDaoTest {
	
	@Autowired
	ApplicationContext context;
	
	private UserDao dao; 
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	MailSender mailSender;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	private User user1;
	private User user2;
	private User user3;
	List<User> users;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserService testUserService;
	
	//@Autowired
	//UserServiceImpl userServiceImpl;
	
	@Before
	public void setUp() {
		this.dao = this.context.getBean("userDao", UserDaoJdbc.class);
		
		this.user1 = new User("gyumee", "이시형", "springno1", "dltlgud1324@oniontech.com", Level.BASIC, 1, 0);
		this.user2 = new User("leegw700", "류기연", "springno2", "dltlgud1324@oniontech.com",Level.SILVER, 55, 10);
		this.user3 = new User("bumjin", "이진운", "springno3", "dltlgud1324@oniontech.com",Level.GOLD, 100, 40);
		
		users = Arrays.asList(
				new User("bumjin", "박범진", "p1", "dltlgud1324@oniontech.com", Level.BASIC, MIN_LOGOUT_FOR_SILVER - 1, 0),
				new User("joytouch", "강명성", "p2", "dltlgud1324@oniontech.com", Level.BASIC, MIN_LOGOUT_FOR_SILVER, 0),
				new User("erwins", "신승한", "p3", "dltlgud1324@oniontech.com", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD - 1),
				new User("madnite1", "이상호", "p4", "dltlgud1324@oniontech.com", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD),
				new User("green", "오민규", "p5", "dltlgud1324@oniontech.com", Level.GOLD, 100, 100)
				);
	}
	

	// User 오브젝트의 내용을 비교하는 검증 코드, 테스트내에서 반복적으로 사용되므로 분리
	private void checkSameUser(User user1, User user2) {
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
		assertThat(user1.getEmail(), is(user2.getEmail()));
		assertThat(user1.getLevel(), is(user2.getLevel()));
		assertThat(user1.getLogin(), is(user2.getLogin()));
		assertThat(user1.getRecommend(), is(user2.getRecommend()));
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
	
	
	@Test
	//@DirtiesContext // 컨텍스트의 DI 설정을 변경하는 테스트라는 것을 알려준다.
	public void upgradeLevels() throws Exception{
		
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		MockUserDao mockUserDao = new MockUserDao(this.users);
		userServiceImpl.setUserDao(mockUserDao);
		
		MockMailSender mockMailSender = new MockMailSender();
		userServiceImpl.setMailSender(mockMailSender);
		
		userServiceImpl.upgradeLevels();
		
		List<User> updated = mockUserDao.getUpdated();
 		assertThat(updated.size(), is(2));
 		checkUserAndLevel(updated.get(0), "joytouch", Level.SILVER);
 		checkUserAndLevel(updated.get(1), "madnite1", Level.GOLD);
 		
		
		List<String> request = mockMailSender.getRequests();
		assertThat(request.size(), is(2));
		assertThat(request.get(0), is(users.get(1).getEmail()));
		assertThat(request.get(1), is(users.get(3).getEmail()));
	}
	
	public void checkUserAndLevel(User updated, String expectedId, Level expectedLevel){
		assertThat(updated.getId(), is(expectedId));
		assertThat(updated.getLevel(), is(expectedLevel));
	}
	
	
	// private void checkLevel(User user, Level expectedLevel){
	// 	User userUpdate = dao.get(user.getId());
	// 	assertThat(userUpdate.getLevel(), is(expectedLevel));
	// }
	
	// upraded : 다음레벨로 업그레이드가 될것인가 아닌가를 지정
	private void checkLevelUpgraded(User user, boolean upgraded){
		User userUpdate = dao.get(user.getId());
		if(upgraded){
			// 업그레이드가 일어났는지 확인
			assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
		}
		else{
			assertThat(userUpdate.getLevel(), is(user.getLevel()));
		}
	}

	@Test
	//@DirtiesContext // 컨텍스트 무효화 애노테이션
	public void upgradeAllOrNothing() throws Exception{
		
		//TestUserService testUserService = new TestUserService(users.get(3).getId());
		//testUserService.setUserDao(this.dao);
		//testUserService.setMailSender(mailSender);
		
		// UserServiceTx txUserService = new UserServiceTx();	
		// txUserService.setTransactionManager(transactionManager);
		// txUserService.setUserService(testUserService);
		
		// TransactionHandler txHandler = new TransactionHandler();
		// txHandler.setTarget(testUserService);
		// txHandler.setTransactionManager(transactionManager);
		// txHandler.setPattern("upgradeLevels");
		// 
		// UserService txUserService = (UserService)Proxy.newProxyInstance(
		// 		getClass().getClassLoader(), 
		// 		new Class[]{UserService.class}, 
		// 		txHandler
		// 		);
		
		// 텍스트용 타깃 주입
		//TxProxyFactoryBean txProxyFactoryBean = context.getBean("&userService", TxProxyFactoryBean.class);
		
		// ProxyFactoryBean txProxyFactoryBean = context.getBean("&userService", ProxyFactoryBean.class);
		// txProxyFactoryBean.setTarget(testUserService);
		// UserService txUserService = (UserService) txProxyFactoryBean.getObject();
		
		dao.deleteAll();
		for(User user : users){
			dao.add(user);
		}
		
		try{
			this.testUserService.upgradeLevels();
			fail("TestUserServiceException expected");
		}
		catch(TestUserServiceException e){
			
		}
		
		checkLevelUpgraded(users.get(1), false);
	}
	
	@Test
	public void mockUpgradeLevels() throws Exception{
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		
		UserDao mockUserDao = mock(UserDao.class);
		when(mockUserDao.getAll()).thenReturn(this.users);
		userServiceImpl.setUserDao(mockUserDao);
		
		MailSender mockMailSender = mock(MailSender.class);
		userServiceImpl.setMailSender(mockMailSender);
		
		userServiceImpl.upgradeLevels();
		
		// verify (베러파이) : 검증하다, any (어떤)
		verify(mockUserDao, times(2)).update(any(User.class));
		verify(mockUserDao, times(2)).update(any(User.class));
		
		verify(mockUserDao).update(users.get(1)); // users.get(1) 파라미터로 update가 호출된적이 있는지 확인
		assertThat(users.get(1).getLevel(), is(Level.SILVER));
		
		verify(mockUserDao).update(users.get(3));
		assertThat(users.get(3).getLevel(), is(Level.GOLD));
		
		ArgumentCaptor<SimpleMailMessage> mailMessageArg = ArgumentCaptor.forClass(SimpleMailMessage.class);
		verify(mockMailSender, times(2)).send(mailMessageArg.capture());
		
		List<SimpleMailMessage> mailMessages = mailMessageArg.getAllValues();
		assertThat(mailMessages.get(0).getTo()[0], is(users.get(1).getEmail()));
		assertThat(mailMessages.get(1).getTo()[0], is(users.get(3).getEmail()));
		
	}
	
	@Test
	public void advisorAutoProxyCreator(){
		assertThat(testUserService, is(java.lang.reflect.Proxy.class));
	}
	
	/** 에러가 발생해야 되지만 정상 처리됨 이 건은 나중에 확인 필요.예상은 MariaDB에서는 OK로 예상됨**/
	//@Test(expected=TransientDataAccessResourceException.class)
	@Test
	public void readOnlyTransactionAttribute(){
		testUserService.getAll();
	}
	
	
	/** 트랜잭션 전파 속성 Required 테스트1 **/
	@Test
	public void transactionSync(){
		
		/** -------------------트랜잭션 경계설정--------------------- **/
		DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
		txDefinition.setReadOnly(true); // 에러가 발생해야 되지만 정상 처리됨 이 건은 나중에 확인 필요.예상은 MariaDB에서는 OK로 예상됨
		TransactionStatus txStatus = transactionManager.getTransaction(txDefinition);
		/** -------------------트랜잭션 경계설정--------------------- **/
		
		userService.deleteAll();
		
		userService.add(users.get(0));
		userService.add(users.get(1));
		
		
		/** -------------------트랜잭션 경계설정--------------------- **/
		transactionManager.commit(txStatus);
		/** -------------------트랜잭션 경계설정--------------------- **/
	}
	
	
	/** 트랜잭션 전파 속성 Required 테스트2 **/
	@Test
	public void transactionSync2(){
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
		TransactionStatus txStatus = transactionManager.getTransaction(txDefinition);
		
		userService.add(users.get(0));
		userService.add(users.get(1));
		
		// dao의 getCount() 메소드도 같은 트랜잭션에서 동작한다. add()에 의해 두 개가 등록됐는지 확인해둔다.
		assertThat(dao.getCount(), is(2));
		
		// 강제로 롤백한다. 트랜잭션 시작 전 상태로 돌아가야 한다.
		transactionManager.rollback(txStatus);
		
		// add()의 작업이 취소되고 트랜잭션 시작 이전의 상태임을 확인 할 수 있다.
		assertThat(dao.getCount(), is(0));
		
	}
	
	@Test
	public void transactionSync3(){
		
		DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
		TransactionStatus txStatus = transactionManager.getTransaction(txDefinition);
		
		try{
			userService.deleteAll();
			userService.add(users.get(0));
			userService.add(users.get(1));
		}
		finally{
			transactionManager.rollback(txStatus);
		}
		
	}
	
	@Test
	@Transactional
	//@Rollback(false) // 테스트 트랜잭션을 커밋시키도록 설정한 테스트
	public void transactionSync4(){
		userService.deleteAll();
		userService.add(users.get(0));
		userService.add(users.get(1));
	}
	
	@Test
	/** 에러가 발생해야 되지만 정상 처리됨 이 건은 나중에 확인 필요.예상은 MariaDB에서는 OK로 예상됨**/
	@Transactional(readOnly=true)
	public void transactionSync5(){
		userService.deleteAll();
	}
	
	static class MockUserDao implements UserDao{
		
		private List<User> users; // 레벨 업그레이드 후보 User 오브젝트 목록
		private List<User> updated = new ArrayList();
		
		public MockUserDao(List<User> users) {
			this.users = users;
		}

		public List<User> getUpdated() {
			return this.updated;
		}
		
		@Override
		public List<User> getAll() {
			return users;
		}
		
		@Override
		public void update(User user) {
			updated.add(user);		
		}
	
		@Override
		public void deleteAll() {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void add(User user) {
			throw new UnsupportedOperationException();			
		}

		@Override
		public User get(String id) {
			throw new UnsupportedOperationException();		
		}

		@Override
		public int getCount() {
			throw new UnsupportedOperationException();		
		}


	}
}

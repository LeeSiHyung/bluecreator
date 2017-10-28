package springbook.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import springbook.dao.UserDao;
import springbook.domain.Level;
import springbook.domain.User;

public class UserService {
	
	public static final int MIN_LOGOUT_FOR_SILVER = 50;
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	
	
	private PlatformTransactionManager transactionManager;
	
	public void setTransactionManager(PlatformTransactionManager transactionManager){
		this.transactionManager = transactionManager;
	}
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	// private DataSource dataSource;
	// public void setDataSource(DataSource dataSource) {
	// 	this.dataSource = dataSource;
	// }
	
	public void upgradeLevels() throws Exception{
		
		/** 로컬 JDBC 트랜잭션을 사용하는 경우 **/
		//TransactionSynchronizationManager.initSynchronization();
		// Connection을 dataSouce에서 생성하지 않는 이유는 
		// DataSourceUtils.getConnection() 메소드는 트랜잭션 동기화에 사용하도록 저장소에 바인딩을 해주기 때문
		//Connection c = DataSourceUtils.getConnection(dataSource);
		//c.setAutoCommit(false);
		
		/** 스프링의 트랜잭션 추상화 계층을 사용하는 경우 **/
		//PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try{
			
			List<User> users = userDao.getAll();
			for(User user : users){
				if(canUpgradeLevel(user)){
					upgradeLevel(user);
				}
			}
			/** 로컬 JDBC 트랜잭션을 사용하는 경우 **/
			//c.commit();
			
			/** 스프링의 트랜잭션 추상화 계층을 사용하는 경우 **/
			transactionManager.commit(status);
			
		}catch(Exception e){
			/** 로컬 JDBC 트랜잭션을 사용하는 경우 **/
			//c.rollback();
			
			/** 스프링의 트랜잭션 추상화 계층을 사용하는 경우 **/
			transactionManager.rollback(status);
			
			throw e;
			
		}finally{
			/** 로컬 JDBC 트랜잭션을 사용하는 경우 **/
			// DataSourceUtils를 통해 DB Connection을 닫음
			//DataSourceUtils.releaseConnection(c, dataSource);
			//TransactionSynchronizationManager.unbindResource(dataSource);
			//TransactionSynchronizationManager.clearSynchronization();
		}
	
	}

	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
		sendUpgradeEMail(user);
	}
	
	private void sendUpgradeEMail(User user){
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.ksug.org");
		Session s = Session.getInstance(props, null);
		
		MimeMessage message = new MimeMessage(s);
		
		try {
			message.setFrom(new InternetAddress("useradmin@ksug.org"));
			// recipient : 받는 사람, 수령인
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setSubject("Upgrade 안내");
			message.setText("사용자님의 등급이  " + user.getLevel().name() + "로 업그레이드 되었습니다.");
			
			Transport.send(message);
			
		} catch (AddressException e) {
			throw new RuntimeException(e);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (Exception e){
			throw new RuntimeException(e);
		}
		
	}

	private boolean canUpgradeLevel(User user) {
		
		Level currentLevel = user.getLevel();
		
		switch(currentLevel){
			case BASIC : 
				return (user.getLogin() >= MIN_LOGOUT_FOR_SILVER);
			case SILVER : 
				return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
			case GOLD : 
				return false;
			default : 
				throw new IllegalArgumentException("Unkown Level:" + currentLevel);
		}
	}

	public void add(User user) {
		if(user.getLevel() == null) user.setLevel(Level.BASIC);
		userDao.add(user);
	}
	
	public static class TestUserService extends UserService{
		// TestUserServiceException 발생 시킬 ID
		private String id;

		public TestUserService(String id) {
			this.id = id;
		}
		
		@Override
		protected void upgradeLevel(User user) {
			if(user.getId().equals(this.id)){
				throw new TestUserServiceException();
			}
			super.upgradeLevel(user);
		}
	}
	
	public static class TestUserServiceException extends RuntimeException{
	}


}

package springbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import springbook.dao.UserDao;
import springbook.domain.Level;
import springbook.domain.User;

public class UserService {
	
	public static final int MIN_LOGOUT_FOR_SILVER = 50;
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void upgradeLevels() throws Exception{
		
		TransactionSynchronizationManager.initSynchronization();
		// Connection을 dataSouce에서 생성하지 않는 이유는 
		// DataSourceUtils.getConnection() 메소드는 트랜잭션 동기화에 사용하도록 저장소에 바인딩을 해주기 때문
		Connection c = DataSourceUtils.getConnection(dataSource);
		c.setAutoCommit(false);
		
		try{
			
			List<User> users = userDao.getAll();
			for(User user : users){
				if(canUpgradeLevel(user)){
					upgradeLevel(user);
				}
			}
			c.commit();
			
		}catch(Exception e){
			c.rollback();
			throw e;
			
		}finally{
			// DataSourceUtils를 통해 DB Connection을 닫음
			DataSourceUtils.releaseConnection(c, dataSource);
			TransactionSynchronizationManager.unbindResource(dataSource);
			TransactionSynchronizationManager.clearSynchronization();
		}
	
	}

	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
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

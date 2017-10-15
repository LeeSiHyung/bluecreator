package springbook.service;

import java.util.List;

import springbook.dao.UserDao;
import springbook.domain.Level;
import springbook.domain.User;

public class UserService {
	
	public static final int MIN_LOGOUT_FOR_SILVER = 50;
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	
	UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void upgradeLevels(){
		List<User> users = userDao.getAll();
		for(User user : users){
			if(canUpgradeLevel(user)){
				upgradeLevel(user);
			}
		}
	}

	private void upgradeLevel(User user) {
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

}

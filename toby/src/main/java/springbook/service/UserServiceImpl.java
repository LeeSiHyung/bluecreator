package springbook.service;

import java.util.List;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import springbook.dao.UserDao;
import springbook.domain.Level;
import springbook.domain.User;

public class UserServiceImpl implements UserService{
	
	public static final int MIN_LOGOUT_FOR_SILVER = 50;
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	private MailSender mailSender;
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void upgradeLevels(){
		List<User> users = userDao.getAll();
		for(User user : users){
			if(canUpgradeLevel(user)){
				upgradeLevel(user);
			}
		}
	}

	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
		sendUpgradeEMail(user);
	}
	
	private void sendUpgradeEMail(User user){
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setFrom("useradmin@ksug.org");
		mailMessage.setSubject("Upgrade 안내");
		mailMessage.setText("사용자님의 등급이 " + user.getLevel().name());
		
		mailSender.send(mailMessage);
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
	
	public static class TestUserServiceImpl extends UserServiceImpl{
		// TestUserServiceException 발생 시킬 ID
		private String id = "madnite1"; // 테스트 픽스처의 users(3)의 ud 값을 고정시킨다.

		
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

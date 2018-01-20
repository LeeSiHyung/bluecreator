package springbook.service;

import java.util.List;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;

import springbook.dao.UserDao;
import springbook.domain.Level;
import springbook.domain.User;

//@Transactional
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
	
	public static class TestUserService extends UserServiceImpl{
		// TestUserServiceException 발생 시킬 ID
		private String id = "madnite1"; // 테스트 픽스처의 users(3)의 ud 값을 고정시킨다.

		
		@Override
		protected void upgradeLevel(User user) {
			if(user.getId().equals(this.id)){
				throw new TestUserServiceException();
			}
			super.upgradeLevel(user);
		}
		
		@Override
		public List<User> getAll() {
			// 강제로 쓰기 시도를 한다. 여기서 읽기전용 속성으로 인한 예외가 발생해야 한다.
			for(User user : super.getAll()){
				super.update(user);
			}
			// 메소드가 끝나기 전에 예외가 발생해야 하니 리턴 값은 별 의미가 없다.
			return null; 
		}
	}
	
	public static class TestUserServiceException extends RuntimeException{
	}

	@Override
	public User get(String id) {
		return userDao.get(id);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public void deleteAll() {
		userDao.deleteAll();
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}
}

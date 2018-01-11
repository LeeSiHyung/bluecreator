package springbook.service;

import java.util.List;

import springbook.domain.User;

public interface UserService {
	
	public void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	void update(User user);
	
	public void upgradeLevels();

}

package springbook.dao;

import java.util.List;

import springbook.domain.User;

public interface UserDao {
	void deleteAll();
	void add(final User user);
	User get(String id);
	int getCount();
	List<User> getAll();
}

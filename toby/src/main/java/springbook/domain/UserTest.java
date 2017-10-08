package springbook.domain;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class UserTest {

	User user;
	
	@Before
	public void setUp(){
		user = new User();
	}
	
	@Test
	public void upgradeLevel(){
		Level[] levels = Level.values();
		for(Level level : levels){
			if(level.nextLevel() == null) continue;
			user.setLevel(level);
			System.out.println("old Level: " + level);
			user.upgradeLevel();
			assertThat(user.getLevel(), is(level.nextLevel()));
			System.out.println("user.getLevel() : " + user.getLevel() + ", " + "level.nextLevel() : " + level.nextLevel());
		}
	}
	
	@Test(expected=IllegalStateException.class)
	public void cannotUpgradeLevel(){
		Level[] levels = Level.values();
		for(Level level : levels){
			if(level.nextLevel() != null) continue;
			user.setLevel(level);
			user.upgradeLevel();
		}
	}
}

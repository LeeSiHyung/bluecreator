package springbook.service.sql;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import springbook.exception.SqlNotFoundException;
import springbook.exception.SqlUpdateFailureException;

public abstract class AbstractUpdatableSqlRegistryTest {
	UpdatableSqlRegistry sqlRegistry;
	
	
	// 테스트 픽스처를 생성하는 부분만 추상 메소드로 만들어두고 서브클래스에서 이를 구현하도록 만든다.
	abstract protected UpdatableSqlRegistry createUpdatableSqlRegistry();
	
	
	@Before
	public void setUp(){
		// sqlRegistry = new ConcurrentHashMapSqlRegistry();
		sqlRegistry = createUpdatableSqlRegistry();
		sqlRegistry.registerSql("KEY1", "SQL1");
		sqlRegistry.registerSql("KEY2", "SQL2");
		sqlRegistry.registerSql("KEY3", "SQL3");
	}
	
	@Test
	public void find(){
		checkFindResult("SQL1", "SQL2", "SQL3");
	}
	
	protected void checkFindResult(String expected1, String expected2, String expected3){
		assertThat(sqlRegistry.findSql("KEY1"), is(expected1));
		assertThat(sqlRegistry.findSql("KEY2"), is(expected2));
		assertThat(sqlRegistry.findSql("KEY3"), is(expected3));
	}
	
	@Test(expected=SqlNotFoundException.class)
	public void unkownKey(){
		sqlRegistry.findSql("SQL9999!@#$");
	}
	
	@Test
	public void updateSingle(){
		sqlRegistry.updateSql("KEY2", "Modify2");
		checkFindResult("SQL1", "Modify2", "SQL3");
	}
	
	@Test
	public void updateMulti(){
		Map<String, String> sqlmap = new HashMap<String, String>();
		
		sqlmap.put("KEY1", "Modify1");
		sqlmap.put("KEY3", "Modify3");
		
		sqlRegistry.updateSql(sqlmap);
		checkFindResult("Modify1", "SQL2", "Modify3");
	}
	
	@Test(expected=SqlUpdateFailureException.class)
	public void updateWithNotExistingKey(){
		sqlRegistry.updateSql("SQL9999!@#$", "");
	}
}

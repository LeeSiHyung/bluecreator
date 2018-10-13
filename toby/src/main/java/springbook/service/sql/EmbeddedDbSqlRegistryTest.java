package springbook.service.sql;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import springbook.exception.SqlUpdateFailureException;

import static org.junit.Assert.fail;


public class EmbeddedDbSqlRegistryTest extends AbstractUpdatableSqlRegistryTest{
	
	EmbeddedDatabase db;
	
	@Override
	protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
		
		db = new EmbeddedDatabaseBuilder()
		.setType(HSQL).addScript("classpath:springbook/service/sql/schema.sql")
		.build();
		
		EmbeddedDbSqlRegistry embeddedDbSqlRegistry = new EmbeddedDbSqlRegistry();
		embeddedDbSqlRegistry.setDataSource(db);
		
		return embeddedDbSqlRegistry;
	}
	
	@After
	public void tearDown(){
		db.shutdown();
	}
	
	@Test
	public void transactionalUpdate(){
		checkFind("SQL1", "SQL2", "SQL3");
		
		Map<String, String> sqlmap = new HashMap<String, String>();
		sqlmap.put("KEY1", "Modified1");
		sqlmap.put("KEY9999!@#$", "Modified9999"); // 이 부분이 실패
		
		try{
			sqlRegistry.updateSql(sqlmap);
			
			// 예외가 발생해서 catch 블록으로 넘어가지 않으면 뭔가 잘못된 것이다. 
			// 그 때는 테스트를 강제로 실패하게 만들고 기대와 다르게 동작한 원인을 찾도록 해야 한다.
			fail();
		}
		catch(SqlUpdateFailureException e){}
		
		checkFind("SQL1", "SQL2", "SQL3");
	}

}

 package springbook.service.sql;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

public class EmbeddedDbTest {
	
	EmbeddedDatabase db;
	SimpleJdbcTemplate template; // JdbcTemplate을 더 편리하게 사용할 수 있게 확장한 템플릿
	
	@Before
	public void setUp(){
		db = new EmbeddedDatabaseBuilder()
		.setType(HSQL)
		.addScript("classpath:/springbook/service/sql/schema.sql")
		.addScript("classpath:/springbook/service/sql/data.sql")
		.build();
		
		// EmbeddedDatabase는 DataSource의 서브 인터페이스이므로 DataSource를 필요로 하는 SimpleJdbcTemplate을 만들 때 사용할 수 있다.
		template = new SimpleJdbcTemplate(db);
	}
	
	@After
	public void tearDown(){
		db.shutdown();
	}
	
	@Test
	public void initData(){
		assertThat(template.queryForInt("select count(*) from sqlmap"), is(2));
		
		List<Map<String, Object>> list = template.queryForList("select * from sqlmap order by key_");
		
		assertThat(list.get(0).get("key_"), is("KEY1"));
		assertThat(list.get(0).get("sql_"), is("SQL1"));
		assertThat(list.get(1).get("key_"), is("KEY2"));
		assertThat(list.get(1).get("sql_"), is("SQL2"));
	}
	
	@Test
	public void insert(){
		template.update("insert into sqlmap(key_, sql_) values(?,?)", "KEY3", "SQL3");
		assertThat(template.queryForInt("select count(*) from sqlmap"), is(3));
	}
	

}

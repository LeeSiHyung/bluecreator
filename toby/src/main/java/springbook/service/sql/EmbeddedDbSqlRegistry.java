package springbook.service.sql;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import springbook.exception.SqlNotFoundException;
import springbook.exception.SqlUpdateFailureException;

public class EmbeddedDbSqlRegistry implements UpdatableSqlRegistry{

	SimpleJdbcTemplate jdbc;
	
	public void setDataSource(DataSource dataSource){
		
	}
	
	@Override
	public void registerSql(String key, String sql) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findSql(String key) throws SqlNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSql(String key, String sql)
			throws SqlUpdateFailureException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSql(Map<String, String> sqlmap)
			throws SqlUpdateFailureException {
		// TODO Auto-generated method stub
		
	}
	
}

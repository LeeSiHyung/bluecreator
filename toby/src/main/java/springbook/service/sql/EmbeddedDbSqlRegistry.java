package springbook.service.sql;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import springbook.exception.SqlNotFoundException;
import springbook.exception.SqlUpdateFailureException;

public class EmbeddedDbSqlRegistry implements UpdatableSqlRegistry{

	SimpleJdbcTemplate jdbc;
	
	public void setDataSource(DataSource dataSource){
		jdbc = new SimpleJdbcTemplate(dataSource);
	}
	
	@Override
	public void registerSql(String key, String sql) {
		jdbc.update("INSERT INTO SQLMAP(KEY_, SQL_) VALUES (?,?)", key, sql);
	}

	@Override
	public String findSql(String key) throws SqlNotFoundException {
		try{
			return jdbc.queryForObject("SELECT SQL_ from SQLMAP WHERE KEY_ = ?", String.class, key);
		}catch(EmptyResultDataAccessException e){
			throw new SqlNotFoundException(key + "에 해당하는 SQL을 찾을 수 없습니다.", e);
		}
	}
	
	@Override
	public void updateSql(String key, String sql)
			throws SqlUpdateFailureException {
		
		int affected = jdbc.update("UPDATE SQLMAP SET SQL_ = ? WHERE KEY_ = ? ", sql, key);
		
		if(affected == 0){
			throw new SqlUpdateFailureException(key + "에 해당하는 SQL을 찾을 수 없습니다.");
		}
	}

	@Override
	public void updateSql(Map<String, String> sqlmap)
			throws SqlUpdateFailureException {
		
		for(Map.Entry<String, String> entry : sqlmap.entrySet()){
			updateSql(entry.getKey(), entry.getValue());
		}
		
	}
	
}

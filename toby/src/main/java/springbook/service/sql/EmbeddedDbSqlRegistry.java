package springbook.service.sql;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import springbook.exception.SqlNotFoundException;
import springbook.exception.SqlUpdateFailureException;

public class EmbeddedDbSqlRegistry implements UpdatableSqlRegistry{

	SimpleJdbcTemplate jdbc;
	// JdbcTemplate과 트랜잭션을 동기화해주는 트랜잭션 템플릿이다. 멀티스레드 환경에서 공유가 가능하다.
	TransactionTemplate transactionTemplate;
	
	public void setDataSource(DataSource dataSource){
		jdbc = new SimpleJdbcTemplate(dataSource);
		
		transactionTemplate = new TransactionTemplate(
			// dataSource로 TransactionManager를 만들고 이를 이용해 TransactionTemplate을 생성한다.
			new DataSourceTransactionManager(dataSource)
		);
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
	// 익명 내부 클래스로 만들어지는 콜백 오브젝트 안에서 사용되는 것이라 final로 선언해줘야 한다.
	public void updateSql(final Map<String, String> sqlmap)
			throws SqlUpdateFailureException {
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			
			// 트랜잭션 템플릿이 만드는 트랜잭션 경계 안에서 동작할 코드를 콜백 형태로 만들고 TransactionTemplate의 execute() 메소드가 전달한다.
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				for(Map.Entry<String, String> entry : sqlmap.entrySet()){
					updateSql(entry.getKey(), entry.getValue());
				}				
			}
		});
		
	
		
	}
	
}

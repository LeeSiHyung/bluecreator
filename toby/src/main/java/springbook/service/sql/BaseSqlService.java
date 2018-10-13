package springbook.service.sql;

import javax.annotation.PostConstruct;

import springbook.exception.SqlNotFoundException;
import springbook.exception.SqlRetrievalFailureException;

public class BaseSqlService implements SqlService{
	
	protected SqlReader sqlReader;
	protected SqlRegistry sqlRegistry;

	@PostConstruct
	public void loadSql(){
		this.sqlReader.read(this.sqlRegistry);
	}
	
	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		
		try{
			return this.sqlRegistry.findSql(key);
		}
		catch(SqlNotFoundException e){
			throw new SqlRetrievalFailureException(e);
		}
	}

	public void setSqlReader(SqlReader sqlReader) {
		this.sqlReader = sqlReader;
	}

	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}

}

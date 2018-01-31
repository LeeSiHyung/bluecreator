package springbook.service;

import springbook.exception.SqlNotFoundException;

public interface SqlRegistry {
	
	void registerSql(String key, String sql);
	
	String findSql(String key) throws SqlNotFoundException;

}

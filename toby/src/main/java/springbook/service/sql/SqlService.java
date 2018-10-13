package springbook.service.sql;

import springbook.exception.SqlRetrievalFailureException;

public interface SqlService {
	String getSql(String key) throws SqlRetrievalFailureException;
}

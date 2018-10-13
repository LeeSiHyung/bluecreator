package springbook.exception;

public class SqlRetrievalFailureException extends RuntimeException{
	
	
	private static final long serialVersionUID = -6416617882543518275L;
	
	
	public SqlRetrievalFailureException() {
	}
	
	public SqlRetrievalFailureException(SqlNotFoundException e) {
		super(e);
	}

	public SqlRetrievalFailureException(String message) {
		super(message);
	}


}

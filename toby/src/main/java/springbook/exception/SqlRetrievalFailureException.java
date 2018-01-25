package springbook.exception;

public class SqlRetrievalFailureException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6416617882543518275L;

	public SqlRetrievalFailureException(String message){
		super(message);
	}
	
	public SqlRetrievalFailureException(String message, Throwable cause){
		super(message, cause);
	}
}

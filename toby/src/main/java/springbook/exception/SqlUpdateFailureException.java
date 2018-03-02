package springbook.exception;

public class SqlUpdateFailureException extends RuntimeException{
	
	
	private static final long serialVersionUID = -6416617882543518275L;
	
	
	public SqlUpdateFailureException() {
		super();
	}
	
	public SqlUpdateFailureException(String message) {
		super(message);
	}
	
	public SqlUpdateFailureException(String message, Throwable cause){
		super(message, cause);
	}
}

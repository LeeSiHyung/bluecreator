package springbook.exception;

public class SqlNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2986921261738132043L;
	
	public SqlNotFoundException(String message){
		super(message);
	}
	
	public SqlNotFoundException(String message, Throwable cause){
		super(message, cause);
	}

}

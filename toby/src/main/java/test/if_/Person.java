package test.if_;

public interface Person {
	
	String getName();
	default int getId() {
		return 0;
	}
	
}

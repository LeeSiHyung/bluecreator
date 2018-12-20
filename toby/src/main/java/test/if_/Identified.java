package test.if_;

public interface Identified {
	
	default int getId() {
		return Math.abs(hashCode());
	}

}

package test.if_;

public interface IntSequence {
	
	// 인터페이스 기본메소드
	default boolean hasNext() {
		return true;
	};
	
	int next();
	
	// 인터페이스의 정적 메소드
	static IntSequence digitsOf(int n) {
		return new DigitSequence(n);
	}
	

}

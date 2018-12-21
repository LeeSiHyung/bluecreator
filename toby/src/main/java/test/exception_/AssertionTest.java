package test.exception_;


public class AssertionTest {
	
	public static void main(String[] args) {
		test();
	}
	
	static void test() {
		int x = 4;
		assert x == 5 : "머지";
	}

}

package coding_test.etc;

public class Test {
	public static void main(String[] args) {
		System.out.println(solution("011100"));
	}

	static public int solution(String S) {
		// write your code in Java SE 8
		int V =  Integer.valueOf(S, 2);
		int result = 0;
		do {
			// 홀수
			if (V % 2 != 0) {
				V -= 1;
			}
			// 짝수
			else {
				V /= 2;
			}
			++result;
		}
		while(V > 0);
		return result;
	}
}

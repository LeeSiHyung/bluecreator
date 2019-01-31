package coding_test.etc;

public class Iterations {
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(1041));
		System.out.println(solution(1041));
		System.out.println(Integer.toBinaryString(529));
		System.out.println(solution(529));
	}

	static public int solution(int N) {
		// write your code in Java SE 8
		String binaryStr = Integer.toBinaryString(N);
		char[] chrs = binaryStr.toCharArray();
		int cnt = 0;
		int result = 0;
		for (char c : chrs) {
			if (c == '0')
				++cnt;
			else {
				if (result < cnt)
					result = cnt;
				cnt = 0;
			}
		}
		return result;
	}
}

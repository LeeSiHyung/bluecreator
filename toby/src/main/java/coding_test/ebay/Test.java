package coding_test.ebay;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(6)));
	}

	public static long[] solution(long n) {
		long[] answer = new long[2];
		int ptr = 0;
		int[] prime = new int[(int) (n / 2)];
		
		prime[ptr++] = 2;

		for (int i = 3; i <= (n / 2); i+=2) {
			int j;
			for (j = 0; j <= ptr; j++) {
				if (i * prime[j] == n) {
					answer[0] = i;
					answer[1] = j;
					Arrays.sort(answer);
					return answer;
				}
			}
			if(ptr == j) prime[ptr++] = i;
		}
		answer[0] = -1;
		answer[1] = -1;
		return answer;

	}

}

package coding_test;

import java.util.LinkedList;

public class Sort {
	
	/* 
		A[0] = 2    A[1] = 1    A[2] = 1
		A[3] = 2    A[4] = 3    A[5] = 1
		the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3. 
	*/
	
	public static void main(String[] args) {
		System.out.println("{[()()]}");
		System.out.println(solution("{[()()]}"));
	}

	static public int solution(String S) {
		// write your code in Java SE 8	
		
		LinkedList<Character> stack = new LinkedList<Character>();
		
		for(int i=0; i < S.length(); i++) {
			// String charAt()로 char로 한자리씩 자르기
			char c = S.charAt(i);
			
		}
		
		return 1;
	}
}

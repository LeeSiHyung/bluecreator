package coding_test.etc;

import java.util.LinkedList;

public class Stack {
	
	// For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.
	
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

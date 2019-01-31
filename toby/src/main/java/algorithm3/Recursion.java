package algorithm3;

public class Recursion {
	
	public static void main(String[] args) {
		// 문자열 출력
		printChars("ABCDE");
		System.out.println();
		// 문자열 출력 역순
		printCharsReverse("ABCDE");
		System.out.println();
		// 2진수
		printInBinary(10);
		System.out.println();
		//배열의 합
		System.out.println(sum(6, new int[] {1,2,3,4,5,6}));
		
	}
	
	// 문자열 출력
	public static void printChars(String str) {
		if(str.length() == 0)
			return;
		else {
			System.out.print(str.charAt(0));
			printChars(str.substring(1));
		}
	}
	
	// 문자열 출력 역순
	public static void printCharsReverse(String str) {
		if(str.length() == 0)
			return;
		else {
			printCharsReverse(str.substring(1));
			System.out.print(str.charAt(0));
		}
	}
	
	// 2진수
	public static void printInBinary(int n) {
		if(n < 2) {
			System.out.print(n);
		}
		else {
			// 2로 나눈 값을 재귀
			printInBinary(n/2);
			// 2로 나눈 몫을 출력
			System.out.print(n%2);
		}
		
	}
	
	// 배열의 합
	public static int sum(int n, int[] data) {
		if(n <= 0)
			return 0;
		else
			return sum(n-1, data) + data[n-1];
	}


}

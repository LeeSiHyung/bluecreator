package algorithm.chap02;

import java.util.Scanner;

public class CardConvRev {

	static int cardConvR(int x, int r, char[] d) {
		
		int digits = 0;
		String dchar = "0123456789ABCDEF";
		
		do {
			// 나머지만 저장
			int remainder = x % r;
			
			// 16진수가 있을 수 있기 때문에 10이상 부터는 알파벳으로 변환
			d[digits++] = dchar.charAt(remainder);
			
			// 정수x를 진수 r로 나누고 나눈 값을 다시 x에 저장
			x /= r;
		}
		while(x != 0);
		
		return digits;
	}
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		int no; // 변환하는 정수
		int cd; // 기수
		int dno; // 변환 후의 자릿수
		int retry;
		char[] cno = new char[32];
		
		System.out.println("10 진수를 기수 변환 합니다.");
		
		do {
			
			do {
				System.out.print("변환하는 음이 아닌 정수 : ");
				no = stdIn.nextInt();
			}
			while(no < 0);
			
			
			do {
				System.out.print("어떤 진수로 변환할까요? ");
				cd = stdIn.nextInt();
			}
			while(cd < 2 || cd > 36); // 2진수 보다 크고 35진수보다 낮은 수 입력
			
			dno = cardConvR(no, cd, cno);
			
			// 역순으로 정렬
			for(int i=dno -1; i >= 0; i--) { // i가 0보다 클 때까지 계속 빼기
				System.out.print(cno[i]);
			}
			System.out.println(" 입니다.");
			
			System.out.println("한 번 더 할까요? (1.예/0.아니요) : ");
			retry = stdIn.nextInt();
		}
		while(retry == 1);
		
		
	}
	
}

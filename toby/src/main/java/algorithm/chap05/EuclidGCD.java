package algorithm.chap05;

import java.util.Scanner;

// 유클리드 호제법으로 최대공약수 구하기
public class EuclidGCD {
	
	static int gcd(int x, int y) {
		
		// 두번째 호출 부터 y는 나머지이다. 즉 나머지가 0이면 나누는 기준 x가 최대 공약수이다.
		if(y == 0) {
			return x;
		}
		
		// 나눈수 y와 나머지의 최대 공약수를 다시 구한다.
		return gcd(y, (x % y));
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("두 정수의 최대공약수를 구합니다.");
		
		System.out.print("정수를 입력하세요."); int x = stdIn.nextInt();
		System.out.print("정수를 입력하세요."); int y = stdIn.nextInt();
		
		System.out.println("최대 공약수는 " + gcd(x , y) + " 입니다. ");
		
	}

}

package algorithm.chap01;

import java.util.Scanner;

public class Median {
	
	static int med3(int a, int b , int c) {
		
		// if(a >= b) {
		// 	
		// 	/** 2 번째 조건  a = b **/
		// 	
		// 	/** 3 번째 조건  a > b **/
		// 	
		// 	// b > c, b = c
		// 	if( b >= c) {
		// 		// 1) a = b = c
		// 		// 3) a = b > c
		// 		// 1) a > b > c
		// 		// 2) a > b = c
		// 		return b;
		// 	}
		// 	// c > a , a = c
		// 	else if(a <= c){
		// 		// 2) c > a = b
		// 		// 3) c > a > b
		// 		// 4) c = a > b
		// 		return a;
		// 	}
		// 	
		// 	else {
		// 		// b와  a가 아닐 경우 기본 c
		// 		return c;
		// 	}
		// }
		// 
		// /** 1 번째 조건 b > a **/
		// 
		// else if (a > c){
		// 	// 4) b > a > c
		// 	return a;
		// }
		// else if (b > c ) {
		// 	// 3) b > a = c
		// 	return c;
		// }
 		// else {
 		// 	// 1) c = b > a
 		// 	// 2) c > b > a
		// 	return b;
		// }
		
		
		if((b >= a && c <= a) || b <= a && c >= a) {
			return a;
		}
		else if( a > b && c < b || a < b && c > b) {
			return b;
		}
		else {
			return c;
		}
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("세 정수의 중앙값을 구합니다.");
		System.out.print("a의 값 : ");
		int a = stdIn.nextInt();
		System.out.print("b의 값 : ");
		int b = stdIn.nextInt();
		System.out.print("c의 값 : ");
		int c = stdIn.nextInt();
		
		System.out.println("중앙값은 " + med3(a,b,c) + " 입니다.");
		
	}

}

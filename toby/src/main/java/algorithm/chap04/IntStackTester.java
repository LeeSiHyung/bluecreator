package algorithm.chap04;

import java.util.Scanner;

public class IntStackTester {
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		IntStack s= new IntStack(64);
		
		while(true) {
			System.out.println("현재 데이터 수 : " + s.size() + " / " + s.capacity());
			System.out.print("(1) 푸시 (2) 팝");
		}
		
		
	}

}

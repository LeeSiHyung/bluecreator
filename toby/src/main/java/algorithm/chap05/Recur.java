package algorithm.chap05;

import java.util.Scanner;

import algorithm.chap04.IntStack;

public class Recur {
	
	static void recur(int n) {
		// if(n > 0) {
		// 	recur(n - 1);
		// 	System.out.println(n);
		// 	recur(n - 2);
		// }
		
		// while(n > 0) {
		// 	recur(n - 1);
		// 	System.out.println(n);
		// 	n = n - 2;
		// }
		
		// recur(n - 1) 끝이 나야만 프린트함.
		
		IntStack s = new IntStack(n);
		// 계속 반복시킨다.
		while(true) {
			// 스텍에 n 값을 푸쉬한 후 n-1 의 값이 0이 될 때까지 푸쉬한다.
			if(n > 0) {
				s.push(n);
				n = n - 1;
				// 0 이하가 되지 않는 한 아래 로직을 수행되지 않도록 continue;
				continue;
			}
			
			// n의 값이 모두 push되면 아래 로직을 수행한다. 수행될 때는 스택에 데이터가 존재해야 한다.
			if(!s.isEmpty()) {
				// 가장 마지막에 저장된 값을 pop한다.
				n = s.pop(); //
				System.out.println(n);
				
				// 프린트한 후 n을 -2를 수행한다. 2보다 큰 값이 존재한다면 위의 스택에 다시 저장됨.
				n = n - 2;
				continue;
			}
			break;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요 : ");
		
		int x = stdIn.nextInt();
		
		recur(x);
		
		// 정수를 입력하세요 : 4
		// 1
		// 2
		// 3
		// 1
		// 4
		// 1
		// 2
		
	}

}

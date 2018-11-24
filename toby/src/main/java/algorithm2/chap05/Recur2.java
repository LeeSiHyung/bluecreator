package algorithm2.chap05;

import algorithm2.chap04.IntStack;

public class Recur2 {
	
	// static void recur(int n) {
	// 	if(n > 0) {
	// 		recur(n - 1);
	// 		System.out.println(n);
	// 		recur(n - 2);
	// 	}
	// }
	
	static void recur(int n) {
		IntStack s = new IntStack(10);
		while(true) {
			// n이 0이하이면 무시
			if(n > 0) {
				// stack 푸쉬
				s.push(n);
				n = n - 1;
				// 상단 while로직을 계속 타야 되기 때문에
				continue;
			}
			// 스택이 들어있는지?
			if(!s.isEmpty()) {
				n = s.pop();
				// 스택에서 가장 마지막 데이터를 출력한다 예상으로는 1부터임
				System.out.println(n);
				n = n - 2;
				// 상단 while로직을 계속 타야 되기 때문에
				continue;
			}
			
			break;
		}
		
	}
	
	public static void main(String[] args) {
		recur(4);
		/**
		1
		2
		3
		1
		4
		1
		2
		**/
	}
}

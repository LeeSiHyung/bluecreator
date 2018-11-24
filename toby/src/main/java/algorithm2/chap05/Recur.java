package algorithm2.chap05;

import algorithm2.chap04.IntStack;

public class Recur {
	
	static void recur(int n) {
		IntStack s = new IntStack(10);
		//if(n > 0) {
		// while(n > 0) {
		// 	recur(n - 1);
		// 	System.out.println(n);
		// 	//recur(n - 2);
		// 	n = n - 2;
		// }
		
		while(true) {
			if(n > 0) {
				s.push(n);
				n = n - 1;
				// continue를 해야 다시 상단 while 부터 순서대로 처리함.
				continue;
			}
			
			if(!s.isEmpty()) {
				// n > 0 이 아닌 가장 작은 수 1이 처음에 pop 된다.
				n = s.pop();
				System.out.println(n);
				// 1이 팝되고  아래 n-2를 하면 음수가 나오기 때문에 기존 재귀함수 처럼 n>0 이하의 데이터는 무시한다.
				// 다만 N이 3부터 는 다시 1을 push 하고 pop 이 되는 순서대로 흐른다. 이 방식은 재귀함수와 동일하다.
				// 프린트 한 N을 가지고 N-2를 한다.
				n = n - 2;
				// continue를 해야 다시 상단 while 부터 순서대로 처리함.
				continue;
			}
			// 최종 n이 0 이하가 되고, 스택에 데이터가 존재하지 않으면, break 된다.
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

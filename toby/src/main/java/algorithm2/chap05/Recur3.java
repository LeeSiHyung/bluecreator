package algorithm2.chap05;

import algorithm2.chap04.IntStack;

public class Recur3 {
	
	// static void recur(int n) {
	// 	if(n > 0) {
	// 		recur(n-1);
	// 		System.out.println(n);
	// 		recur(n-2);
	// 	}
	// }
	
	static void recur(int n) {
		
		IntStack s = new IntStack(10);
		
		while(true) {
			if(n > 0) {
				s.push(n);
				n = n - 1;
		 		continue;
			}
			
			if(!s.isEmpty()) {
				n = s.pop();
				System.out.println(n);
		 		n = n - 2;
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

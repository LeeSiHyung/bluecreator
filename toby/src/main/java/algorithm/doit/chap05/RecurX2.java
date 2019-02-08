package algorithm.doit.chap05;

import java.util.ArrayDeque;
import java.util.Deque;

public class RecurX2 {
	
	static void recur(int n) {
		Deque<Integer> stack = new ArrayDeque<Integer>();
		while(true) {
			if(n > 0) {
				stack.push(n);
				n = n - 1;
				continue;
			}
			
			if(!stack.isEmpty()) {
				n = stack.pop();
				System.out.println(n);
				n = n - 2;
				continue;
			}
			break;
		}
		
	}
	
	public static void main(String[] args) {
		recur(4);
	}

}

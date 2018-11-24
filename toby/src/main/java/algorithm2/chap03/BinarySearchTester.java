package algorithm2.chap03;

import java.util.Arrays;

public class BinarySearchTester {

	public static void main(String[] args) {
		
		int key = 120;
		int[] x = {15,27,39,77,92,108,121};
		
		int idx = Arrays.binarySearch(x, key);
		if(idx == -1)
			System.out.println("없음");
		else
			System.out.println("x[" + idx + "] 에 있다.");
		
	}
}

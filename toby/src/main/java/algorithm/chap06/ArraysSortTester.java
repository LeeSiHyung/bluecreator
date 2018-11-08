package algorithm.chap06;

import java.util.Arrays;

public class ArraysSortTester {
	
	public static void main(String[] args) {
		
		int num = 7;
		int[] x = {22, 5, 11, 32, 120, 68, 70};
		
		Arrays.sort(x);
		
		System.out.println("오름차순으로 정렬합니다.");
		for(int i=0; i < num; i++) {
			System.out.println("x[" + i  + "]=" + x[i]);
		}
	}
}

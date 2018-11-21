package algorithm2.chap02;

import java.util.Scanner;

public class ReverseArray {
	
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	static void reverse(int[] a) {
		// 배열을 역순으로 정렬할 때 총 교환횟수는 length / 2 이다.
		for(int i=0; i < a.length / 2; i++) {
			swap(a, i, a.length - i - 1);
		}
	}
	
	public static void main(String[] args) {
		// Scanner stdIn = new Scanner(System.in);
		// 
		// System.out.print("요솟수 : ");
		// int num = stdIn.nextInt();
		// int[] x = new int[num];
		// 
		// for(int i=0; i < num; i++) {
		// 	System.out.print("x[" + i + "] : ");
		// 	x[i] = stdIn.nextInt();
		// }
		
		int[] x = {10, 73, 2, -5, 42};
		
		reverse(x);
		
		System.out.println("요소를 역순으로 정렬했습니다.");
		for(int i=0; i < x.length; i++)
			System.out.println("x[" + i + "]=" + x[i]);
		
	}

}

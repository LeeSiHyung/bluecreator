package algorithm.chap06;

import java.util.Scanner;

import algorithm.chap04.IntStack;

public class QuickSort2 {
	
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	static void quickSort(int[] a, int left, int right) {
		IntStack lstack = new IntStack(right-left+1);
		IntStack rstack = new IntStack(right-left+1);
		
		lstack.push(left);
		rstack.push(right);
		
		while(lstack.isEmpty() != true) {
			int pl = left = lstack.pop();
			int pr = right = rstack.pop();
			int x = a[(left + right) / 2];
			
			do {
				while(a[pl] < x) pl++;
				while(a[pr] > x) pr--;
				if(pl <= pr)
					swap(a, pl++, pr--);
			}
			while(pl <= pr);
			
			// 재귀 호출 부분
			if(left < pr) {
				lstack.push(left);
				rstack.push(pr);   
			}
			
			if(pl < right) {                                                                                                  
				lstack.push(pl);
				rstack.push(right);
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("퀵 정렬");
		//System.out.print("요솟수 : ");
		
		//int nx = stdIn.nextInt();
		//int[]x = new int[nx];
		int nx = 9;
		int[]x = {5, 8, 4, 2, 6, 1, 3, 9, 7};
		
		// for(int i=0; i < nx; i++) {
		// 	System.out.print("x[" + i + "] : ");
		// 	x[i] = stdIn.nextInt();
		// }
		
		quickSort(x, 0, nx-1);
		
		System.out.println("오름차순으로 정렬했습니다.");
		for(int i=0; i < nx; i++)
			System.out.println("x[" + i + "]=" + x[i]);
	}
}

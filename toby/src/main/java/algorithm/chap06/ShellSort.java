package algorithm.chap06;

import java.util.Scanner;

public class ShellSort {
	
	static void shellSort(int[] a, int n) {
		for(int h= n/2; h>0; h/=2) {
			// 4,5,6,7..
			for(int i=h; i<n; i++) {
				int j;
				int tmp = a[i];
				// 0~4, 1~5, 2~6, 3~7..
				for(j=i-h; j>=0 && a[j]>tmp; j-= h)
					a[j+h] = a[j];
				a[j+h] = tmp;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("셸 정렬");
		System.out.print("요솟수 : ");
		
		int nx = stdIn.nextInt();
		int[] x = new int[nx];
		
		for(int i=0; i < nx; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}
		
		shellSort(x, nx);
		
		System.out.println("오름차순으로 정렬했습니다.");
		for(int i=0; i<nx; i++) {
			System.out.println("x[" + i + "] = " + x[i]);
		}
	}

}

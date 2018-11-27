package algorithm2.chap06;

import java.util.Scanner;

public class QuickSort {
	
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	static void quickSort(int[] a, int left, int right) {
		int pl = left;
		int pr = right;
		int x = a[(pl + pr) / 2];
		
		do {
			while(a[pl] < x) pl++;
			while(a[pr] > x) pr--;
			if(pl <= pr)
				swap(a, pl++, pr--);
		}
		while(pl <= pr);
		
		
		if(left < pr) quickSort(a, left, pr);
		if(pl < right) quickSort(a, pl, right);
		
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int nx = 9;
		int[] x = {1,8,7,4,5,2,6,3,9};
		
		quickSort(x, 0, nx-1);
		
		for(int i=0; i < nx; i++) {
			System.out.print(x[i] + " ");
		}
	} 
}

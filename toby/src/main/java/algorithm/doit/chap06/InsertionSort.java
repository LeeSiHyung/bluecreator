package algorithm.doit.chap06;

import java.util.Arrays;

public class InsertionSort {
	
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	static void insertionSort(int[] a, int n) {
		for(int i=0; i < n; i++) {
			int j;
			int tmp = a[i];
			for(j=i; j > 0 && a[j-1] > tmp; j--) {
				a[j] = a[j-1];
			}
			a[j] = tmp;
		}
	}
	
	public static void main(String[] args) {
		int[] x = new int[] {22, 5, 11, 32, 120, 68, 70};
		
		insertionSort(x, x.length);
		
		System.out.println(Arrays.toString(x));
	}

}

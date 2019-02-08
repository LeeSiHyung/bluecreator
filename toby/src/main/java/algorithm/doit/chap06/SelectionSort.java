package algorithm.doit.chap06;

import java.util.Arrays;

public class SelectionSort {
	
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	static void selectionSort(int[] a, int n) {
		for(int i=0; i < n - 1; i++) {
			int min = i;
			for(int j= i + 1; j < n; j++) {
				if(a[min] > a[j]) {
					min = j;
				}
			}
			swap(a, i, min);
		}
	}
	
	public static void main(String[] args) {
		int[] x = new int[] {22, 5, 11, 32, 120, 68, 70};
		
		selectionSort(x, x.length);
		
		System.out.println(Arrays.toString(x));
		
	}

}

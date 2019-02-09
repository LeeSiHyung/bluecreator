package algorithm.doit.chap06;

import java.util.Arrays;

public interface BubbleSort {
	
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	static void bubbleSort(int[] a, int n) {
		for(int i=0; i < n-1; i++) {
			for(int j=n-1; j > i; j--) {
				if(a[j-1] > a[j]) {
					swap(a, j-1 , j);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		int[] x = new int[] {22, 5, 11, 32, 120, 68, 70};
		
		bubbleSort(x, x.length);
		
		System.out.println(Arrays.toString(x));
		
	}

}

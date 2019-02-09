package algorithm.doit.chap06;

import java.util.Arrays;

public interface QuickSort {
	
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	static void quickSort(int[] a, int left, int right) {
		int pl = left;
		int pr = right;
		int pc = (pl + pr) / 2;
		while(pl <= pr) {
			while(a[pl] < a[pc]) pl++;
			while(a[pr] > a[pc]) pr--;
			if(pl <= pr) {
				swap(a, pl++, pr--);
			}
		}
		if(left < pr) quickSort(a, left, pr);
		if(pl < right) quickSort(a, pl, right);
		
	}
	
	public static void main(String[] args) {
		
		int[] x = new int[] {22, 5, 11, 32, 120, 68, 70};
		
		quickSort(x, 0, x.length - 1);
		
		System.out.println(Arrays.toString(x));
		
	}

}

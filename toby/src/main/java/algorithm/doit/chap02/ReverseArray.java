package algorithm.doit.chap02;

import java.util.Arrays;

public class ReverseArray {
	
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	static void reverse(int[] a) {
		for(int i=0; i < a.length / 2; i++) {
			swap(a, i, a.length - i - 1);
		}
	}
	
	public static void main(String[] args) {
		int x[] = new int[] {10, 73, 2, -5, 42};
		reverse(x);
		System.out.println(Arrays.toString(x));
	}

}

package algorithm.doit.chap03;

import java.util.Arrays;

public class BinSearch {
	
	static int binarySearch(int[] a, int n, int key) {
		int pl = 0;
		int pr = n - 1;
		do {
			int pc = (pl + pr) / 2;
			if(a[pc] == key) return pc;
			else if(a[pc] < key) pl = pc + 1;
			else pr = pc - 1;
			
		}
		while(pl <= pr);
		return -1;
	}
	
	
	public static void main(String[] args) {
		int[] arr = new int[] {15, 27, 39, 77, 92, 108, 121};
		int key = 108;
		System.out.println(binarySearch(arr, arr.length, key));
		System.out.println(Arrays.binarySearch(arr, key));
		
	}

}

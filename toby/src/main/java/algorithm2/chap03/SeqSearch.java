package algorithm2.chap03;

import java.util.Scanner;

public class SeqSearch {
	
	// static int seqSearch(int[] a, int n, int key) {
	// 	int i = 0;
	// 	while(true) {
	// 		if(i == n)
	// 			return -1;
	// 		if(a[i] == key)
	// 			return i;
	// 		i++;
	// 	}
	// }
	
	// static int seqSearch(int[] a, int n, int key) {
	// 	for(int i=0; i < n; i++) {
	// 		if(a[i] == key)
	// 			return i;
	// 	}
	// 	return -1;
	// }
	
	/** 보초법 추가 **/
	static int seqSearch(int[] a, int n, int key) {
		int i=0;
		a[n] = key;
		while(true) {
			if(a[i] == key)
				break;
			i++;
		}
		return (i == n) ? -1 : i;
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int[] x = {22, 8, 55, 32, 120, 55, 70, 0};
		
		int idx = seqSearch(x, x.length-1, 70);
		
		if(idx == -1)
			System.out.println("그 값은 요소가 없습니다.");
		else
			System.out.println("x[" + idx +"]에 있습니다.");
			
	}

}

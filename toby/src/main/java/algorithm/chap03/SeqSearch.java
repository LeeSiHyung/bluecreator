package algorithm.chap03;

import java.util.Scanner;

public class SeqSearch {

	// static int seqSearch(int[] a, int n, int key) {
	// 	int i = 0;
	// 	
	// 	while(true) {
	// 		if(i == n)
	// 			return -1; // 배열의 길이 끝까지 도달했을 경우 -1 리턴
	// 		
	// 		if(a[i] == key)
	// 			return i; // 해당 위치에 있는 idx 값 리턴
	// 		
	// 		i++;
	// 	}
	// }
	
	static int seqSearch(int[] a, int n, int key) {
		for(int i=0; i < n; i++) {
			if(a[i] == key)
				return i; // 해당 위치에 있는 idx 값 리턴
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("요솟수 : ");
		int num = stdIn.nextInt();
		
		int[] x = new int[num];
		
		for(int i=0; i < num; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}
		
		System.out.print("검색할 값 : ");
		int key = stdIn.nextInt();
		
		int idx = seqSearch(x, num, key);
		
		if(idx == -1)
			System.out.println("그 값의 요소가 없습니다.");
		else 
			System.out.println(key + "은(는) x[" + idx + "] 에 있습니다.");
	}
}

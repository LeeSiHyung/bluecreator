package algorithm.chap03;

import java.util.Scanner;

public class BinSearch {
	
	static int binSearch(int[] a, int n, int key) {
		int pl = 0;
		int pr = n - 1;
		
		do {
			
			int pc = (pl + pr) / 2;
			if(a[pc] == key)
				return pc;
			else if(a[pc] < key)
				pl = pc + 1;
			else
				pr = pc - 1;
		}
		// pl이 pr에 도달할 때까지 반복수행
		while(pl <= pr);
		
		// pl == pr은 실패이므로 return -1
		return -1;
	}

	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("요솟수 : ");
		
		int num = stdIn.nextInt();
		int[] x = new int[num];
		
		System.out.println("오름차순으로 입력하세요.");
		
		System.out.print("x[0] : ");
		x[0] = stdIn.nextInt();
		
		for(int i=1; i < num; i++) {
			do {
				System.out.print("x[" + i + "] : ");
				x[i] = stdIn.nextInt();
			}
			while(x[i] < x[i - 1]);
		}
		
		System.out.print("검색할 값 : ");
		int key = stdIn.nextInt();
		
		int idx = binSearch(x, num, key);
		
		if(idx == -1)
			System.out.println("그 값의 요소가 없습니다.");
		else
			System.out.println(key + "는 x[" + idx + "] 에 있습니다.");
	}
}

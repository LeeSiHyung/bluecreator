package algorithm.chap06;

import java.util.Scanner;

public class InsertionSort {
	
	static void insertionSort(int[] a, int n) {
		for(int i=1; i < n; i++) {
			int j;
			int tmp = a[i];
			for(j=i; j > 0 && a[j-1] > tmp; j--)
				// 1, 2, 3, 4, 5, 0, 6 이고 0을 기준할 때
				// 1, 2, 3, 4, 5, 5, 6 으로 점점 오른쪽 기준으로 한자리씩 이동시킨다.
				a[j] = a[j-1];
			
			// for j-- 으로 인해 마지막 j에서 한자리 마이너스됨
			// 1, 1, 2, 3, 4, 5, 6  내려 오기 때문에 가장 첫 자리에 a[0] tmp=0을 삽입한다.
			a[j] = tmp;
		}
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("단순 삽입 정렬");
		System.out.print("요솟수 : ");
		
		int nx = stdIn.nextInt();
		int[] x = new int[nx];
		
		for(int i=0; i < nx; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}
		
		insertionSort(x, nx);
		
		System.out.println("오름차순으로 정렬했습니다.");
		for(int i=0; i<nx; i++) {
			System.out.println("x[" + i + "] = " + x[i]);
		}
	}

}

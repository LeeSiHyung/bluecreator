package algorithm.chap06;

import java.util.Scanner;

public class ShellSort2 {
	
	static void shellSort(int[] a, int n) {
		
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("셸 정렬");
		System.out.print("요솟수 : ");
		
		int nx = stdIn.nextInt();
		int[] x = new int[nx];
		
		for(int i=0; i < nx; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}
		
		shellSort(x, nx);
		
		System.out.println("오름차순으로 정렬했습니다.");
		for(int i=0; i<nx; i++) {
			System.out.println("x[" + i + "] = " + x[i]);
		}
	}

}

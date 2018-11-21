package algorithm2.chap01;

import java.util.Scanner;

public class TriangleLB {
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		int n;
		
		do {
			n = stdIn.nextInt();
		}
		while(n <= 0);
		
		
		for(int i=0; i < n; i++) {
			for(int j=0; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}

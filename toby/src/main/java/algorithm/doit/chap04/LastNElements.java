package algorithm.doit.chap04;

import java.util.Scanner;

public class LastNElements {
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		final int N = 10;
		int[] a = new int[N];
		int cnt = 0;
		int retry;
		
		System.out.println("정수를 입력하세요.");
		
		do {
			System.out.printf("%d번째 정수 : ", cnt+1);
			a[cnt++ % N] = stdIn.nextInt();
			System.out.println("계속 할까요? (예.1/아니오.0) : ");
			retry = stdIn.nextInt();
		}
		while(retry == 1);
		
		// cnt=12면 N=10이면 i는 2부터 시작한다. 2 ~ 12
		int i = cnt - N;
		// cnt가 1에 N이 10이면 -9이기 때문에 0부터 1까지만 조회하게 된다.
		if(i < 0) i = 0;
		for(; i < cnt; i++) {
			System.out.printf("%2d번째의 정수 = %d\n", i+1, a[i % N]);
		}
		
	}

}

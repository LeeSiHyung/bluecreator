package algorithm.chap04;

import java.util.Scanner;

public class LastNElements {
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		final int N = 10;
		int[] a = new int [N];
		int cnt = 0;
		int retry;
		
		System.out.println("정수를 입력하세요.");
		
		do {
			
			System.out.printf("%d번째 정수 : ", cnt + 1);
			a[cnt++ % 10] = stdIn.nextInt();
			
			System.out.print("계속할까요? (예.1 / 아니오.0) : ");
			retry = stdIn.nextInt();
		}
		while(retry == 1);
		
		int i = cnt - N; // cnt = 10, 11, 12, 13... 이면 0, 1, 2... 로 변환
		if(i < 0) i = 0; // 
		
		for(; i < cnt; i++) {
			// 입력을 받을 때 1부터 시작했기 때문에 i + 1을 해준다.
			// a[]는 N을 기준으로 링 버퍼되기 때문에 i % N을 해준다.
			System.out.printf("%2d 번째의 정수 : %d\n", i + 1, a[i % N]);
		}
			
		
	}

}

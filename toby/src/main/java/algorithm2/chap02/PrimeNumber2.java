package algorithm2.chap02;

public class PrimeNumber2 {
	
	public static void main(String[] args) {
		
		int counter = 0;
		
		int ptr = 0; // 소수의 개수
		int[] prime = new int[500];
		
		prime[ptr++] = 2;
		
		// n을 3부터 시작해야  n+2 하면서 홀수만 대상으로 삼을 수 있다.
		for(int n=3; n <= 1000; n+=2) {
			int i;
			// ptr 즉 소수의 갯수만큼만 조회한다. 1부터 시작하는 이유는 2를 제외하기 위해서다.
			for(i = 1; i < ptr; i++) {
				counter++;
				if(n % prime[i] == 0) break;
			}
			
			// ptr을 모두 조회해도 없으면 소수이다.
			if(ptr == i) {
				prime[ptr++] = n;
			}
		}
		
		for(int i=0; i < ptr; i++)
			System.out.println(prime[i]);
		
		System.out.println("end " + counter);
		
	}

}

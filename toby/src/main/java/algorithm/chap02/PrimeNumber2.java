package algorithm.chap02;

public class PrimeNumber2 {
	
	public static void main(String[] args) {
		
		int counter = 0;
		int ptr = 0;
		int[] prime = new int[500];
		
		prime[ptr++] = 2;
		
		// 대상은 홀수만 검색한다.
		for(int n=3; n <=1000; n += 2) {
			int i;
			
			// 이미 저장하고 있는 소수를 나눈다.
			for(i=1; i < ptr; i++) {
				counter ++;
				if(n % prime[i] == 0) {
					break;
				}
			}
			
			// 저장되어 있는 소수에 나누어 떨어지지 않으면 n을 배열에 저장한다.
			if(ptr == i) {
				prime[ptr++] = n;
			}
		}
		
		for(int i=0; i < ptr; i++) {
			System.out.println(prime[i]);
		}
		
		System.out.println("나눗셈을 수행한 횟수 : " + counter);
	}

}

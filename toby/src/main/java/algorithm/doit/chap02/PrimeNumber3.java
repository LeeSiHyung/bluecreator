package algorithm.doit.chap02;

public class PrimeNumber3 {
	
	public static void main(String[] args) {
		
		int counter = 0;
		int ptr = 0;
		int[] prime = new int[500];
		
		prime[ptr++] = 2;
		prime[ptr++] = 3;
		
		for(int n = 5; n <= 1000; n+=2) {
			boolean flag = false;
			// 1000 = (31 * 31 = 961)
			// prime[i]의 소수 31까지만 조회
			for(int i=1; prime[i] * prime[i] <= n; i++) {
				counter += 2;
				if(n % prime[i] == 0) {
					flag = true;
					break;
				}
			}
			// prime[i]의 소수 31까지만 조회 했을 때 flag true가 나오지 않았다면 소수
			if(!flag) {
				prime[ptr++] = n;
				// prime[i] * prime[i] <= n이 성립되지 않는 건도 포함
				counter++;
			}
		}
		for(int i=0; i < ptr; i++) {
			System.out.println(prime[i]);
		}
		
		System.out.println("곱셈과 나눗셈을 수행한 횟수 : " + counter);
	}

}

package algorithm2.chap02;

public class PrimeNumber3 {
	
	public static void main(String[] args) {
		
		int counter = 0;
		int ptr = 0;
		int[] prime = new int[500];
		
		prime[ptr++] = 2;
		prime[ptr++] = 3;
		
		for(int n=5; n <= 1000; n+=2) {
			boolean flag = false;
			// n = 100일때 제곱근인 10 * 10 이후로 수가 교차한다.
			for(int i=1; prime[i] * prime[i] <= n; i++) {
				counter++;
				if(n % prime[i] == 0) {
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				prime[ptr++] = n;
				// prime[i] * prime[i] <= n 이상일 경우 conter 횟수가 추가가 되지 않으므로 이 부분에서도 counter ++를 수행해줘야 한다.
				counter++;
			}
		}
		
		for(int i=0; i < ptr; i++) {
			System.out.println(prime[i]);
		}
		System.out.println("end " + counter);
		
	}

}

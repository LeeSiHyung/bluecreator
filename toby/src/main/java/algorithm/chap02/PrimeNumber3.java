package algorithm.chap02;

public class PrimeNumber3 {
	
	public static void main(String[] args) {
		
		int counter = 0;
		int ptr = 0;
		int[] prime = new int[500];
		
		prime[ptr++] = 2;
		prime[ptr++] = 3;
		
		for(int n=5; n <= 1000; n += 2) {
			
			boolean flag = false;
			
			
			// 예를 들어 100까지 진행했을 때, 소수는 2 * 50, 4 * 25, 5 * 20, 10 * 10을 따라서 반대로 변경된다. 20 * 5, 25 * 4, 50 * 2
			// N이 100이고 소수 중 가장 큰 수가 10 이하일 때 까지를 도는 함수. 물론 10은 소수가 아님
			for(int i=1; prime[i] * prime[i] <= n ; i++) {
				// 위의 곳셈과 아래 나눗셈을 합쳐서 2개로 설정
				counter += 2;
				
				if(n % prime[i] == 0) {
					flag = true;
					break;
				}
			}
			
			// 소수와 나누어 떨어지지 않는 경우에만 저장
			if(!flag) {
				prime[ptr++] = n;
				counter ++;
			}
			
		}
		
		for(int i=0; i < ptr; i++) {
			System.out.println(prime[i]);
		}
		
		System.out.println("counter : " + counter);
	}

}

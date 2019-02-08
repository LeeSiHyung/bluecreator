package algorithm.doit.chap02;

public class PrimeNumber1 {
	
	public static void main(String[] args) {
		
		int counter = 0;
		
		for(int n = 2; n <= 1000; n++) {
			int i;
			for(i = 2; i < n; i++) {
				counter++;
				// 나누어 떨어지면 합성수
				if(n % i == 0) break;
			}
			// i가 n이면 한번도 break가 되지 않았던 뜻이 된다. 즉 소수다.
			if(i == n) System.out.println(n);
		}
		
		System.out.println("나눗셈 수행 횟수 : " + counter);
		
	}

}

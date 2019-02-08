package algorithm.doit.chap02;

public class PrimeNumber2 {
	
	public static void main(String[] args) {
		
		int counter = 0;
		int ptr = 0;
		int[] prime = new int[500];
		
		prime[ptr++] = 2;
		
		for(int n=3; n <= 1000; n += 2) {
			int i;
			for(i=1; i < ptr; i++) {
				counter++;
				if(n % prime[i] == 0)
					break;
			}
			// ptr이  prime 내부 소수의 갯수이기 때문에 ptr == i이면  break가 한번도 수행되지 않았기 때문에 소수라고 판단하면 된다.
			if(ptr == i) prime[ptr++] = n;
		}
		for(int i=0; i < ptr; i++) {
			System.out.println(prime[i]);
		}
		System.out.println("나눗셈을 수행한 횟수 : " + counter);
	}

}

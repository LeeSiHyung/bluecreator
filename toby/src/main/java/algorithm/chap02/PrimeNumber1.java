package algorithm.chap02;

public class PrimeNumber1 {
	
	public static void main(String[] args) {
		int counter = 0;
		
		for(int n = 2; n <= 1000; n++) {
			int i;
			
			for(i=2; i < n; i++) {
				counter ++;
				// 나누어 떨어지면 합성수
				if(n % i == 0) {
					break;
				}
			}
			
			// 위의 for문이 끝나면 i++가 한번 더 발생됨
			if(n == i) {
				System.out.println(i);
			}
			
		}
		System.out.println("나눗셈을 수행한 횟수 : " + counter);
	}

}

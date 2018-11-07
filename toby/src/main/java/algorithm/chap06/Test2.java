package algorithm.chap06;

public class Test2 {
	

	public static void main(String[] args) {
		int n = 7;
		int[] a = {22, 5, 11, 32, 120, 68, 70};
		int h;
		// 1부터 시작하여 값을 3배하고 1을 더하면서 n / 9 을 넘지 않는 가장 큰 값을 h에 대입한다.
		// 초기 값이 너무 크면 효과가 없기 때문에 n / 9을 해준다.
		// n/9이면 n이 7일때 그 다음 로직을 수행하지 않고 h=1로 설정됨
		// n/3이면 n이 7일때 그 다음 로직인 h = h * 3 + 1을 수행한다.
		for(h=1; h < n/3; h = h * 3 + 1);
		
		System.out.println("h 초기 값 : " + h);
		
		for(; h > 0; h/=3) {
			for(int i = h; i < n; i++) {
				// 4 부터 시작하고 i가 6이되면 h도 6이 되기 때문에 다시 1부터 for문이 다시 돈다.
				// System.out.println("h="+ h+ ", i= " + i);
				int j;
				int tmp = a[i]; // 4부터 시작하면 idx 4가 들어간다.
				
				// tmp가 가장 끝수이기 때문에 처음부터 더 큰 수가 이는지 체크한다.
				for(j = i - h; j >= 0 && a[j] > tmp; j-=h)
					// 여기서는 idx 0과 4를 바꿔치기 한다. 
					a[j + h] = a[j]; // 큰수를 오른쪽으로 밀어낸다.
				
				// 상위 for문이 완료 되면 j-h를 수행하기 때문에 0과 4 인덱스라면 0인덱스에 tmp를  넣는 작업을 수행한다.
				a[j + h] = tmp;
			}
		}

	}
	
}

package algorithm.doit.chap03;

public class SeqSearchSen {
	
	static int seqSearchSen(int[] a, int n, int key) {
		int i = 0;
		
		// 배열의 마지막 값에 찾는 값을 넣는다
		a[n] = key;
		
		while(true) {
			if(a[i] == key)
				break;
			i++;
		}
		
		// 만약 찾는 값이 배열의 마지막에 존재한다면 보초이기 때문에 -1을 리턴한다.
		return i == n ? -1 : i;
	}
	
	public static void main(String[] args) {
		
		// int[] 생성시 마지막 데이터가 보초이기때문에 0을 더 추가한다.
		System.out.println(seqSearchSen(new int[]{22, 8, 55, 32, 120, 55, 70, 0}, 7, 70));
		
	}

}

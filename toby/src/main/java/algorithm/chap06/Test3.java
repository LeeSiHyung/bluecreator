package algorithm.chap06;

public class Test3 {
	
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	public static void main(String[] args) {
		
		int nx = 9;
		int[]a = {5, 8, 4, 2, 6, 1, 3, 9, 7};
		int pl = 0;
		int pr = 8;
		
		int x = a[(pl + pr) / 2];
		
		do {
			while(a[pl] < x) pl++;
			while(a[pr] > x) pr--;
			if(pl <= pr) {
				System.out.println(a[pl] + "-" + a[pr]);
				swap(a, pl++, pr--);
			}
		} while(pl <= pr);
		
		for(int i=0; i < nx; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
}

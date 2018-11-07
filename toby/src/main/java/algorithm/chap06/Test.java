package algorithm.chap06;

public class Test {
	

	public static void main(String[] args) {
		int n = 8;
		int a[] = {8,1,4,2,7,6,3,5};
		
		//for(int h= n/2; h>0; h/=2) {
			int h = 4;
			//for(int i=h; i<n; i++) {
				int i = 4;
				int j;
				int tmp = a[4];
				for(j=0; j>=0 && a[j]>tmp; j-= 4)
					a[j+4] = a[j];
				a[j + 4 -4] = tmp;
			//}
		//}

		for(i=0; i < n; i++) {
			System.out.println("x[" + i + "] = " + a[i]);
		}
	}
	
}

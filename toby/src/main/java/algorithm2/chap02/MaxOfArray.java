package algorithm2.chap02;

public class MaxOfArray {
	
	static int maxOf(int[] a) {
		int max = a[0];
		for(int i=0; i < a.length; i++) {
			if(a[i] > max) max = a[i];
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[] a = {12, 23, 33, 1, 22};
		System.out.println(maxOf(a));
	}

}

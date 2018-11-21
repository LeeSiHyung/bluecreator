package algorithm2.chap02;

public class ArrayEqual {
	
	static boolean equals(int[] a, int[] b) {
		if(a.length != b.length)
			return false;
		
		for(int i=0; i < a.length; i++) {
			if(a[i] != b[i]) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		int[] a = {10, 73, 2, -5, 42};
		int[] b = {10, 73, 1, -5, 42};
		
		System.out.println(equals(a, b));
	}

}

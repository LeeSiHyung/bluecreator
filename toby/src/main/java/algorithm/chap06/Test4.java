package algorithm.chap06;

public class Test4 {
	

	public static void main(String[] args) {
		int[] a = {2,4,6,8,11,13};
		int[] b = {1,2,3,4,9,16,21};
		int[] c = new int[13];
		int na = a.length;
		int nb = b.length;
		
		int pa = 0;
		int pb = 0;
		int pc = 0;
		
		while(pa < na && pb < nb)
			c[pc++] = (a[pa] <= b[pb]) ? a[pa++] : b[pb++];
		
		while(pa < na)
			c[pc++] = a[pa++];
		
		while(pb < nb)
			c[pc++] = a[pb++];
			
	}
	
}

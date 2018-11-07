package algorithm.chap06;

public class MergeArray {
	
	static void merge(int[] a, int na, int[] b, int nb, int[] c) {
		int pa = 0;
		int pb = 0;
		int pc = 0;
		
		while(pa < na && pb < nb) {
			c[pc++] = (a[pa] <= b[pb]) ? a[pa++] : b[pb++];
		}
		
		
	}

}

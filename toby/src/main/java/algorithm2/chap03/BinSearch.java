package algorithm2.chap03;

public class BinSearch {
	
	static int binSearch(int[] a, int n, int key) {
		
		int pl = 0;
		int pr = n - 1;
		
		do {
			int pc = (pl + pr) / 2;
			if(a[pc] == key)
				return pc;
			
			else if(a[pc] < key)
				pl = pc + 1;
			
			else if(a[pc] > key)
				pr = pc -1;
		}
		while(pl <= pr);
		
		return -1;
	}
	
	public static void main(String[] args) {
		int key = 121;
		int[] x = {15,27,39,77,92,108,121};
		
		int idx = binSearch(x, x.length, key);
		if(idx == -1)
			System.out.println("없음");
		else
			System.out.println("x[" + idx + "] 에 있다.");
	}

}

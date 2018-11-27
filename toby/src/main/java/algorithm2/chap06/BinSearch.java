package algorithm2.chap06;

public class BinSearch {
	
	
	// 이진 검색은 이미 정렬되었다는 것을 기준으로 함
	static int binSearch(int[] a, int n, int key) {
		int pl = 0;
		int pr = n-1;
		do {
			int pc = (pl + pr) / 2;
			if(a[pc] == key)
				return pc;
			else if(a[pc] < key)
				pl = pc + 1;
			else
				pr = pc - 1;
		}
		while(pl <= pr);
		return -1;
	}
	
	public static void main(String[] args) {
		// Scanner stdIn = new Scanner(System.in);
		int ky = 108;
		int[] x = {15, 27, 39, 77, 92, 108, 121};
		
		int idx = binSearch(x, x.length, ky);
		
		System.out.println("해당 위치는 " + idx + "에 있습니다.");
		
	} 
}

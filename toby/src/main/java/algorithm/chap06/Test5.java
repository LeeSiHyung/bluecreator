package algorithm.chap06;

public class Test5 {
	

	public static void main(String[] args) {
		
		int[] a = {22,5,11,32,120,68,70};
		int n = a.length;
		
		int left = 0;
		int right = n-1;
		int temp = a[left];
		
		int child;
		int parent;
		
		for(parent = left; parent < (right + 1 ) / 2; parent = child) {
			int cl = parent * 2 + 1;
			int cr = cl + 1;
			child = (cr < right && a[cr] > a[cl] ? cr : cl);
			
			// 이미 정렬되어 있는지 확인
			if(temp >= a[child]) break;
			a[parent] = a[child];
		}
		
		a[parent] = temp;
		
			
	}
	
}

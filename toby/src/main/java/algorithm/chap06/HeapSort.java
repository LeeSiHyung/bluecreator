package algorithm.chap06;

public class HeapSort {

	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	static void downHeap(int[] a, int left, int right) {
		
		int temp = a[left];
		int child;
		int parent;
		
		for(parent = left; (parent < (right + 1) / 2); parent = child) {
			
			int cl = parent * 2 + 1;
			int cr = cl + 1;
			
			child = (cr <= right && a[cr] > a[cl] ? cr : cl);
			if(temp >= a[child])
				break;
			
			a[parent] = a[child];
		}
		
		a[parent] = temp;
	}
	
	static void heapSort(int[] a, int n) {
		// n - 1 은 index 값은 항상 1을 빼줘야 하기 때문이다.
		// 나누기 2를 해주는 이유는 이진트리에서 반복 횟수는 그 절반이기 때문이다.
		
		// 아래 로직은 중간부터 루트 순으로 한번씩 정렬을 한다
		for (int i = (n - 1) / 2; i >= 0; i--) {
			downHeap(a, i, n-1);
		}
		
		// 끝과 루트를 한번 바꾼후 재 정렬을 수행한다.
		for(int i= n-1; i > 0; i--) {
			swap(a, 0, i);
			downHeap(a, 0, i - 1);
		}
	}
	
	
	public static void main(String[] args) {
		
		int[] a = {22,5,11,32,120,68,70};
		int n = a.length;
		
		heapSort(a, n);
		
		for(int i=0; i < n; i++) {
			System.out.println("x[" + i + "]=" + a[i]);
		}
		
	}
}

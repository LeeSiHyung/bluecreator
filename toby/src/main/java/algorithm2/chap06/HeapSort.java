package algorithm2.chap06;

public class HeapSort {

	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	static void downHeap(int[] a, int left, int right) {
		// 루트
		int temp = a[left];
		int child;
		int parent;
		
		for(parent = left; parent < (right + 1) / 2; parent = child) {
			int cl = parent * 2 + 1;
			int cr = cl + 1;
			// 왼쪽, 오른쪽 자식 노드 중 큰 값을 child로 설정
			child = (cr <= right && a[cr] > a[cl] ? cr : cl);
			
			// 루트보다 큰 값이 존재할 경우 break;
			if(temp >= a[child])
				break;
			
			// 자식 노드 값을 부모노드에 넣어 준다.
			a[parent] = a[child];
		}
		
		// 상위에서 이미 a[parent] 값이 변경되었기 때문에 루트 값을 대체 해준다.
		a[parent] = temp;
	}
	
}

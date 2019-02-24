package algorithm.doit.chap06;

import java.util.Arrays;

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
		
		for(parent = left; parent < (right + 1) / 2; parent = child) {
			int cl = parent * 2 + 1;
			int cr = cl + 1;
			
			child = (cr <= right && a[cr] > a[cl]) ? cr : cl;
			if(temp >= a[child]) break; // 루트보다 작으면 옮기지 않음
			a[parent] = a[child];
		}
		a[parent] = temp;
	}
	
	
	static void heapSort(int[] a, int n) {
		// (n-1)/2  부터  ~ 0까지가는 이유는 자식 노드를 제외하고 힙을 유지하기 위해서다.
		// 이 for문은 밑에서 부터 루트까지 힙을 만들어감.
		for(int i = (n + 1) / 2; i >= 0; i--) {
			downHeap(a, i, n - 1);
		}
		
		// 위에서 힙을 만들었음
		for(int i = n - 1; i > 0; i--) {
			// 반복하면서 루트와 마지막 노드를 swap
			swap(a, 0, i);
			// left 0은 맨 최상 루트를 힙으로 만듬, 대상은 자식 노드 i-1까지 i는 이미 정렬된 값임, 
			// 당연히 루트가 최댓 값이 됨
			downHeap(a, 0, i - 1);
		}
	}
	
	public static void main(String[] args) {                                 
		int[] x = new int[] {4, 6, 8, 7, 5, 9};
		// int[] x = new int[] {22, 5, 11, 32, 120, 68, 70};
		// int[] x = new int[] {10, 9, 5, 8, 3, 2, 4, 6, 7, 1};
		
		heapSort(x, x.length);
		
		System.out.println(Arrays.toString(x));
	}

}

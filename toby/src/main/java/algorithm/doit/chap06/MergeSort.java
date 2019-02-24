package algorithm.doit.chap06;

import java.util.Arrays;

public class MergeSort {
	
	static int[] buff;
	
	static void __mergeSort(int[] a, int left, int right) {
		
		if(left < right) {
			int i;
			int center = (left + right) / 2;
			int p = 0;
			int j = 0;
			int k = left;
			
			__mergeSort(a, left, center);
			__mergeSort(a, center+1, right);
			
			for(i = left; i <= center; i++) {
				// left ~ conter까지 담은 수 buff
				buff[p++] = a[i];
			}
			
			while(i <= right && j < p) {
				// j의 경우 buff 0 부터 시작 i의 경우는 center + 1 부터  right 까지 조회
				// k의 경우 left ~ right 까지의 값을 담음 1 ~ 10일 때 5 ~ 10을 담을 필요가 있기 땨문에 k는 left부터 시작
				a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];
			}
			
			while(j < p) {
				a[k++] = buff[j++];
			}
			
		}
	}
	
	static void mergeSort(int[] a, int n) {
		buff = new int[n];
		
		__mergeSort(a, 0, n - 1);
		
		buff = null;
	}
	
	public static void main(String[] args) {
		
		int[] x = new int[] {22, 5, 11, 32, 120, 68, 70};
		
		mergeSort(x, x.length);
		
		System.out.println(Arrays.toString(x));
		
	}

}

package algorithm.inflearn;

public class Recursion {
	
	public static void main(String[] args) {
		// 문자열 출력
		printChars("ABCDE");
		System.out.println();
		// 문자열 출력 역순
		printCharsReverse("ABCDE");
		System.out.println();
		// 2진수
		printInBinary(10);
		System.out.println();
		//배열의 합
		System.out.println(sum(6, new int[] {1,2,3,4,5,6}));
		// 배열 검색
		System.out.println(search(new int[] {1,2,3,4,5,6,7,8,9}, 0, 8, 9));
		// 최댓값 검색
		System.out.println(findMax(new int[] {1, 100, 23, 45, 22, 101}, 0, 5));
		// 이진검색
		System.out.println(binarySearch(new String[] {"0", "1", "2", "3"}, "3", 0, 4));
		
	}
	
	// 문자열 출력
	public static void printChars(String str) {
		if(str.length() == 0)
			return;
		else {
			System.out.print(str.charAt(0));
			printChars(str.substring(1));
		}
	}
	
	// 문자열 출력 역순
	public static void printCharsReverse(String str) {
		if(str.length() == 0)
			return;
		else {
			printCharsReverse(str.substring(1));
			System.out.print(str.charAt(0));
		}
	}
	
	// 2진수
	public static void printInBinary(int n) {
		if(n < 2) System.out.print(n);
		else {
			printInBinary(n/2);
			System.out.print(n%2);
		}
	}
	
	// 배열의 합
	public static int sum(int n, int[] data) {
		if(n <= 0)
			return 0;
		else
			return sum(n-1, data) + data[n-1];
	}
	
	// **** 배열검색 ****
	public static int search(int[] data, int begin, int end, int target) {
		if(begin > end) return -1;
		else {
			int middle = (begin + end) / 2;
			if(data[middle] == target) return middle;
			int index = search(data, begin, middle-1, target);
			// begin ~ middle 까지 찾아서 -1이 아니라면 index;
			if(index != -1) return index;
			// begin ~ middle이 -1이 된다면 middle ~ end까지 존재하기 때문에 다시 탐색
			else return search(data, middle + 1, end, target);
		}
	}
	
	// 최댓값 검색
	// public static int findMax(int[] data, int begin, int end) {
	// 	if(begin >= end) return data[begin];
	// 	// 현재 begin 값과 begin +1 값을 비교, begin이 max가 될때까지 비교
	// 	else return Math.max(data[begin], findMax(data, begin+1, end));
	// }
	
	// 최댓값 검색
	public static int findMax(int[] data, int begin, int end) {
		// 최댓값을 검색하기 때문에 값은 무조건 존재하고, begin이 end까지 도달했다면 begin의 값을 리턴해준다.
		if(begin == end) return data[begin];
		else {
			// middle을 통해서 검색 범위가 절반씩 줄어듬
			int middle = (begin + end) / 2;
			int f1 = findMax(data, begin, middle);
			int f2 = findMax(data, middle+1, end);
			return Math.max(f1, f2);
		}
	}
	
	// 이진검색
	public static int binarySearch(String[] items, String target, int begin, int end) {
		// 이진검색의 경우 값이 존재하지 않을 수 있다.
		if(begin > end) return -1;
		else {
			int middle = (begin+end) / 2;
			// int cond = items[middle].compareTo(target);
			// ** 위의 로직은 오류 발생 기준이 되는 값 target을 기준으로 compareTo 함수를 호출해야 한다. **/
			int cond = target.compareTo(items[middle]);
			if(cond == 0) return middle;
			else if(cond < 0) return binarySearch(items, target, begin, middle-1);
			else return binarySearch(items, target, middle+1, end);
		}
	}
	
	

}

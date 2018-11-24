package algorithm2.chap03;

import java.util.Arrays;

public class StringBinarySearch {
	
	public static void main(String[] args) {
		
		// 자연정렬이 아닌 문자열 정렬 순으로 idx를 가져옴
		String key = "텍스트2";
		String[] x = {
			"텍스트1",	
			"텍스트2",	
			"텍스트10",	
			"텍스트21",	
			"텍스트100",	
		};
		
		
		int idx = Arrays.binarySearch(x, key);
		if(idx == -1)
			System.out.println("없음");
		else
			System.out.println("x[" + idx + "] 에 있다.");

	}

}

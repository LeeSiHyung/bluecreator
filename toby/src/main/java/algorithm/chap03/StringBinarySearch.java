package algorithm.chap03;

import java.util.Arrays;
import java.util.Scanner;

public class StringBinarySearch {
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		String[] x = {
				"abstract", "assert", "boolean", "break", "byte",
				"case", "continue", "for", "switch", "try", "while",
		};
		
		System.out.print("원하는 키워드를 입력하세요 : ");
		
		String key = stdIn.next();
		
		int idx = Arrays.binarySearch(x, key);
		
		if(idx < 0)
			System.out.println("해당 키워드가 없습니다.");
		else
			System.out.println("해당 키워드는 x[" + idx + "] 에 있습니다.");
		
		
	}

}

package algorithm2.chap06;

import java.util.Scanner;

public class BmMatch {
	
	
	static int bmMatch(String txt, String pat) {
		return -1;
	}
	
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("텍스트 : ");
		String s1 = stdIn.nextLine();
		System.out.print("패턴 : ");
		String s2 = stdIn.nextLine();
		
		int idx = bmMatch(s1, s2);
		if(idx == -1)
			System.out.println("일치하지 않습니다.");
		else
			System.out.println("일치합니다.");
		
	}

}

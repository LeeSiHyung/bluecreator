package algorithm2.chap08;

import java.util.Scanner;

public class KmpMatch {
	
	
	static int kmpMatch(String txt, String pat) {
		
		int pt = 1;
		int pp = 0;
		
		int[] skip = new int[pat.length() + 1];
		
		skip[pt] = 0;
		
		while(pt != pat.length()) {
			if(pat.charAt(pt) == pat.charAt(pp))
				skip[++pt] = ++pp;
			else if(pp == 0)
				skip[++pt] = pp;
			else
				pp = skip[pp];
		}
		
		
		pt = pp = 0;
		
		while(pt != txt.length() && pp != pat.length()) {
			if(txt.charAt(pt) == pat.charAt(pp)) {
				pt++;
				pp++;
			}
			else if(pp == 0) {
				pt++;
			}
			else
				pp = skip[pp];
		}
		
		if(pp == pat.length())
			return 1;
		
		return -1;

	}
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("텍스트 : ");
		String s1 = stdIn.next();
		
		System.out.print("패턴 : ");
		String s2 = stdIn.next();
		
		int idx = kmpMatch(s1, s2);
		
		if(idx == -1) {
			System.out.println("일치하지 않습니다.");
		}
		else {
			System.out.println("일치합니다.");
		}
			

		
		
	}

}

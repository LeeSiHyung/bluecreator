package algorithm.chap08;

public class BFmatch {
	
	static int bfMatch(String txt, String pat) {
		
		int pt = 0; // 텍스트 idx
		int pp = 0; // 텍스트와 pat이 일치할 때까지의 idx
		
		while(pt != txt.length() && pp != pat.length()) {
			if(txt.charAt(pt) == pat.charAt(pp)) {
				pt++;
				pp++;
			}
			else {
				// 텍스트 idx와 pat이 일치하지 않으므로 일치되었던 idx pp를 pt에서 빼고 다음 포인트를 가리킨다.
				pt = pt - pp + 1;
				pp = 0;
			}
		}
		
		if(pp == pat.length())
			return pt - pp;
		
		return -1;
		
	}
	
	public static void main(String[] args) {
		String s1 = "ABC이지스이지스스DEF";
		String s2 = "이지스스";
		
		int idx = bfMatch(s1, s2);
		
		if(idx == -1)
			System.out.println("텍스트에 패턴이 없습니다.");
		else {
			int len = 0;
			for(int i=0; i < idx; i++)
				len += s1.substring(i, i+1).getBytes().length;
			len += s2.length();
			
			System.out.println((idx + 1) + "번째 문자부터 일치합니다.");
			System.out.println("텍스트 : " + s1);
			System.out.printf(String.format("패턴 : %%%ds\n", len), s2);
		}
	}
}

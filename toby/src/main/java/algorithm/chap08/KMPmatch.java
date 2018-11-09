package algorithm.chap08;

public class KMPmatch {
	
	static int kmpMatch(String txt, String pat) {
		int pt = 1; // 텍스트 커서
		int pp = 0; // 패턴 커서
		int[] skip = new int[pat.length() + 1]; // 건너뛰기 표
		
		// 건너뛰기 표를 만들기
		skip[pt] = 0;
		while(pt != pat.length()) {
			if(pat.charAt(pt) == pat.charAt(pp))
				// 패턴 스트링에 동일하게 반복하는 글자가 있을 때마다 해당 index에 pp가 1증가된 값을 넣는다.
				skip[++pt] = ++pp; 
			else if(pp == 0)
				skip[++pt] = pp;
			else
				pp = skip[pp]; // ?
		}
		
		// 검색
		pt = pp = 0;
		while(pt != txt.length() && pp != pat.length()) {
			if(txt.charAt(pt) == pat.charAt(pp)) {
				pt++;
				pp++;
			}
			else if(pp == 0)
				pt++;
			else
				pp = skip[pp]; // ABCABD 이면 AB가 두개이기 때문에 뒤의  AB 위치에서 다시 조회를 시작한다.
		}
		
		if(pp == pat.length())
			return pt - pp;
		
		return -1;
	}
	
	public static void main(String[] args) {
		String s1 = "ABC이지스이지스스DEF";
		String s2 = "이지스스";
		
		int idx = kmpMatch(s1, s2);
		
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

package algorithm.chap08;

public class BMmatch {
	
	static int bmMatch(String txt, String pat) {
		int pt;
		int pp;
		
		int txtLen = txt.length();
		int patLen = pat.length();
		int[] skip = new int[Character.MAX_VALUE + 1];
		
		
		// 건너뛰기 표 만들기
		for(pt = 0; pt <= Character.MAX_VALUE; pt++) 
			skip[pt] = patLen;
		
		for(pt = 0; pt < patLen - 1; pt++)
			skip[pat.charAt(pt)] = patLen - pt - 1;
		
		// 검색
		while(pt < txtLen) {
			pp = patLen -1; // pt가 중복되는 값이 없으면 4칸씩 이동하기 때문에 이동할 때마다 pat 마지막 값과 비교를 한다.
			
			// 마지막 값이 일치한다면.
			while(txt.charAt(pt) == pat.charAt(pp)) {
				if(pp == 0)
					return pt;	// 검색 성공
				
				// 만약 마지막 값이 일치한다면 각각 커서를 줄여주면서 값을 비교한다.
				pp--;
				pt--;
			}
			
			// skip[txt.charAt(pt)]이 만약 건너뛰기 표에 존재하지 않는다면 초기 값 patLen 즉 4가 리턴될 것임
			
			pt += (skip[txt.charAt(pt)] > patLen - pp) ? skip[txt.charAt(pt)] : patLen - pp;
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		String s1 = "ABCXDEZCABACABAC";
		String s2 = "ABAC";
		
		int idx = bmMatch(s1, s2);
		
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

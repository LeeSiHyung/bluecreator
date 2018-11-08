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
				skip[++pt] = ++pp;
			else if(pp == 0)
				skip[++pt] = pp;
			else
				pp = skip[pp];
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		kmpMatch("", "");
	}

}

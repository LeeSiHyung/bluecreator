package algorithm.doit.chap02;

public class CardConvRev {
	
	static int cardConvR(int x, int r, char[] d) {
		int digits = 0;
		String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUWXYZ";
		do {
			// 나머지가 역순으로 저장됨. 출력할때는 역순으로 출력해야 한다.
			d[digits++] = dchar.charAt(x % r);
			x /= r;
		}
		while(x != 0);
		return digits;
	}
	
	public static void main(String[] args) {
		char[] c = new char[32];
		int len = cardConvR(50,2,c);
		for(int i = len - 1; i >= 0; i--) {
			System.out.print(c[i]);
		}
	}

}

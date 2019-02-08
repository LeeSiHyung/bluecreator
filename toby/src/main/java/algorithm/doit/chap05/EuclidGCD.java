package algorithm.doit.chap05;

public class EuclidGCD {
	
	// 유클리드 호제법
	static int gcd(int x, int y) {
		if(y == 0) return x;
		else return gcd(y, x % y);
	}
	
	public static void main(String[] args) {
		System.out.println(gcd(22, 8));
	}

}

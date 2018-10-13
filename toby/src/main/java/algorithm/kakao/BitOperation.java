package algorithm.kakao;

public class BitOperation {
	
	public static void main(String[] args) {
		
		int a = 170;
		int b = 245;
		
		// 논리곱(and) & 양쪽 모두 1 이면 1, 아니면 0 을 반환한다.
		// 논리합(or) | 한쪽이 1 이면 1, 그렇지 않으면 0 을 반환한다.
		// 배타적 논리함(xor) ^ 	두 비트가 다르면 1 같으면 0.
		
		// Integer클래스에 해당 기능을 지원하는 메소드가 있습니다. toBinaryString, toOctalString, toHexString 2진수 8진수 16진수로 바꿔주는 메소드입니다.
		// 반대로 Integer.valueOf(문자열,진수); 를 이용하면 첫번째 매개변수에 들어간 문자열을 두번째 매개변수에 해당하는 진수로 바꿔서 int형으로 반환해줍니다.
		
		System.out.println(Integer.valueOf(Integer.toBinaryString(a), 2));
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		System.out.println(String.format("%08d", new Integer(Integer.toBinaryString(a ^ b))));
		
	}

}

package test;

import static org.mockito.Matchers.intThat;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MathTest {
	
	public static void main(String[] args) {
		
		System.out.println(Math.floorMod(25, 12));
		System.out.println(Math.pow(10, 2));
		System.out.println(Math.sqrt(100));
		
		// 언제나 동일한 결과를 낼 필요가 있는 경우 StrictMath 를 사용해야 한다.
		System.out.println(StrictMath.floorMod(25, 12));
		System.out.println(StrictMath.pow(10, 2));
		System.out.println(StrictMath.sqrt(100));
		try {
			System.out.println(Math.multiplyExact(1000000000, 3));			
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		
		System.out.println(test());
		
		float f = 123456789;
		System.out.println(f);
		
		double x = 3.75;
		int n = (int) x;
		System.out.println(n);
		
		n = (int) Math.round(x);
		System.out.println(n);
		
		int j = 1;
		char next = (char)('J' + j);
		System.out.println(next);
		
		int b = (int) 3000000000L;
		System.out.println(b);
		
		try {
			b = Math.toIntExact(3000000000L);
			System.out.println(b);
		}
		catch(Exception e) {
			System.err.println(e);
		}
		
		
		System.out.println(BigInteger.valueOf(876543210123456789L));
		System.out.println(new BigInteger("876543210123456789"));
		System.out.println(BigInteger.ZERO);
		System.out.println(BigInteger.ONE);
		System.out.println(BigInteger.TEN);
		
		BigInteger b1 = BigInteger.valueOf(10);
		BigInteger b2 = BigInteger.valueOf(10);
		BigInteger r = BigInteger.valueOf(10).multiply(b1.add(b2));
		System.out.println(r);
		
		System.out.println(BigDecimal.valueOf(2,0).subtract(BigDecimal.valueOf(11, 1)));
		System.out.println(BigDecimal.valueOf(11, 2));
		System.out.println(BigDecimal.valueOf(11, 3));
		
	}
	
	// strictfp 제어자를 추가하면 해당 메서드의 모든 부동소수점 연산은 전체 플랫폼에서 같은 결과를 반환한다. 즉 이식성이 높아진다.
	strictfp public static double test() {
		return 0.001;
	}

}

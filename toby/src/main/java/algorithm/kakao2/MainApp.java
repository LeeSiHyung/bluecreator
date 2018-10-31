package algorithm.kakao2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainApp {
	
	
	static int sum[] = new int[3];
	
	public static void main(String[] args) {
		// 	1S2D*3T	37	1^1 * 2 + 2^2 * 2 + 3^3
		// 	1D2S#10S	9	1^2 + 2^1 * (-1) + 10^1
		// 	1D2S0T	3	1^2 + 2^1 + 0^3
		// 	1S*2T*3S	23	1^1 * 2 * 2 + 2^3 * 2 + 3^1
		// 	1D#2S*3S	5	1^2 * (-1) * 2 + 2^1 * 2 + 3^1
		// 	1T2D3D#	-4	1^3 + 2^2 + 3^2 * (-1)
		// 	1D2S3T*	59	1^2 + 2^1 * 2 + 3^3 * 2
		
		run("1S2D*3T");
		run("1D2S#10S");
		run("1D2S0T");
		run("1S*2T*3S");
		run("1D#2S*3S");
		run("1T2D3D#");
		run("1D2S3T*");
		
	}
	
	public static void run(String msg) {
		// System.out.println(msg + " - " + 0);
		
		String regex = "(\\d{1,2}[S|T|D][*|#]{0,1})";
		// () 괄호 하나 단위로 찾기 때문에  총 3번 반복 
		Pattern p = Pattern.compile(regex + regex + regex);
		Matcher m = p.matcher(msg);
		m.find();
		// 자바 정규식은 1부터
		for(int i=1; i<= m.groupCount(); i++) {
			// 위는 3번 반복에 대한 정규식, 아래는 1건씩에 대한 정규식 3개를 괄호로 묶는다.
			Pattern p1 = Pattern.compile("(\\d{1,2})([S|T|D])([*|#]{0,1})");
			Matcher m1 = p1.matcher(m.group(i));
			m1.find();
			//System.out.println(m1.group(1) + " " + m1.group(2) + " " + m1.group(3) );
			
			// i가 1 부터 시작하기 때문에 -1을 해준다. 제곱근 pow
			sum[i-1] = (int)Math.pow(Integer.parseInt(m1.group(1)), 
					getPow(m1.group(2)));
			
			setOption(i, m1.group(3));
		} 
		
		System.out.println(msg + " - " + (sum[0] + sum[1] + sum[2]) );
	}
	
	public static void setOption(int idx, String m) {
		if("*".equals(m)) {
			sum[idx - 1] *= 2;
			if(idx > 1) {
				sum[idx - 2] *= 2;
			}
		}
		else if("#".equals(m)){
			sum[idx - 1 ] *= -1;
		}
	}
	
	public static int getPow(String m) {
		int val = 0;
		
		if("S".equals(m)) {
			val = 1;
		}
		else if("D".equals(m)) {
			val = 2;
		}
		else if("T".equals(m)) {
			val = 3;
		}
		
		return val;
	}
	
	
}

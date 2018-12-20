package test;

public class StringTest {
	public static void main(String[] args) {
		
		// String join 메소드
		String names  = String.join(", ", "Peter", "Paul", "Mary", "SiHyeong", "GiYeon");
		System.out.println(names);
	
		// StringBuilder 사용
		StringBuilder sb = new StringBuilder();
		sb.append("Peter1 ");
		sb.append("Peter2 ");
		sb.append("Peter3 ");
		sb.append("Peter4 ");
		System.out.println(sb.toString());
		
		
		String[] result = sb.toString().split("\\s+");
		System.out.println(result[2]);
		
		
		String location = null;
		if("test".equals(location)) {
			System.out.println("true");
		}
		else {
			System.out.println("false");
		}
		
		try {
			if(location.equals("test")) {
				System.out.println("true");
			}
			else {
				System.out.println("false");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("리터널 문자열을 앞에 둬야 한다. 아니면 null pointer 발생됨");
		}
	}
}

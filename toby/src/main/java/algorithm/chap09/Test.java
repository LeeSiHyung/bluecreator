package algorithm.chap09;

public class Test {
	
	 public static void main(String[] args) {
	 	String a = Integer.toBinaryString(1) ;
	 	String b = Integer.toBinaryString(2) ;
	 	String c = Integer.toBinaryString(1 | 2);
	 	System.out.printf("%02d\n", Integer.parseInt(a));
	 	System.out.printf("%02d\n", Integer.parseInt(b));
	 	System.out.printf("%02d\n", Integer.parseInt(c));
	 	
	 	System.out.println("A".compareTo("B"));
	 	System.out.println("A".compareTo("A"));
	 	System.out.println("C".compareTo("A"));
	 }
}

package algorithm.chap01;

public class Max3m {
	
	static int max3(int a, int b, int c) {
		
		int max = a;
		
		if(b > max) {
			max = b;
		}
		
		if(c > max) {
			max = c;
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		System.out.println("max3(3,2,1) = " + max3(3, 2 ,1));
		System.out.println("max3(5,3,1) = " + max3(5, 3, 1));
		System.out.println("max3(6,2,7) = " + max3(6, 2, 7));
		System.out.println("max3(3,7,1) = " + max3(3, 2 ,1));
		System.out.println("max3(3,5,1) = " + max3(3, 2 ,1));
	}

}

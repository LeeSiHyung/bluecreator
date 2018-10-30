package algorithm.chap02;

public class ForTest {
	public static void main(String[] args) {
		
		
		for(int i=2; i < 10; i++) {
			int j;
			for(j=2; j < i; j++) {
				System.out.println("[S] " + i + ":" + j);
			}
			System.out.println("[E] " + i + ":" + j);
			
		}
	}
}

package algorithm2.chap01;

public class ThePalindrome {
	
	public static void main(String[] args) {
		System.err.println(find("abcccc"));
	}
	
	public static int find(String s) {
		for(int i=s.length(); ; i++) {
			boolean flag = true;
			
			for(int j=0; j < s.length(); j++) {
				if((i - j - 1) < s.length()&&s.charAt(j) != s.charAt(i - j - 1)) {
					flag = false;
					break;
				}
			}
			
			if(flag) return i; 
		}
	}

}

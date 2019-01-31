package coding_test.ebay;

public class Test4 {

	public static void main(String[] args) {
		System.out.println(solution("abcde"));
	}
	

	
	public static int solution(String plain) {
		int len = plain.length();
		for(int i=0; i < len; i++) {
			if(isPalindrome(plain.substring(i))) {
				return i + len;
			}
		}
		
        return len * 2;
    }



	private static boolean isPalindrome(String str) {
		for(int i = 0; i<str.length() / 2; i++){
            if(str.charAt(i) != str.charAt(str.length()-i-1)){   
            	return false;
            }
        }
		return true;
	}
	
}

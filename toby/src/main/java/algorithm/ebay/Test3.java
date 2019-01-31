package algorithm.ebay;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test3 {

	public static void main(String[] args) {
		System.out.println(solution(new int[]{6, 1, 11}));
	}
	
	

	
	public static int[] solution(int[] numbers) {
        
		String[] numStr = new String[] {
	            "Zero"
	          , "One"
	          , "Two"
	          , "Three"
	          , "Four"
	          , "Five"
	          , "Six"
	          , "Seven"
	          , "Eight"
	          , "Nine"
	          };
		
		int[] answer = new int[numbers.length];
		String[] sArr = new String[numbers.length]; 
		Map<String, Integer> map = new HashMap<String, Integer>(); 
		
        for(int i=0; i < numbers.length; i++) {
        	
        	if(numbers[i] > 10) {
        		String token = "";
        		for(String s : String.valueOf(numbers[i]).split("")) {
        			token += numStr[Integer.valueOf(s)];
        		}
        		sArr[i] = token;
        	}
        	else {
        		sArr[i] =  numStr[numbers[i]];
        	}
        	map.put(sArr[i], numbers[i]);
 
        }
        
        Arrays.sort(sArr);
        
        for(int i=0; i <sArr.length; i++) {
        	answer[i] = (map.get(sArr[i]));
        }
        
        return answer;
        
    }
	
}

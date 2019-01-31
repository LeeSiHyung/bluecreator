package algorithm.topcoder;

import java.util.HashMap;
import java.util.Map;

public class InterestingParty {
	
	public static void main(String[] args) {
		
		// System.out.println(bestInvitation1(
		// 		new String[] {"fishing", "gardening", "swimming", "fishing"}, 
		// 		new String[] {"hunting", "fishing", "fishing", "biting"}));
		// 
		// System.out.println(bestInvitation1(
		// 		new String[] {"snakes", "programing", "cobra", "monty"}, 
		// 		new String[] {"python", "python", "anaconda", "python"}));
		// 
		System.out.println(bestInvitation2(
				new String[] {"fishing", "gardening", "swimming", "fishing"}, 
				new String[] {"hunting", "fishing", "fishing", "biting"}));
		
		System.out.println(bestInvitation2(
				new String[] {"snakes", "programing", "cobra", "monty"}, 
				new String[] {"python", "python", "anaconda", "python"}));
	}
	
	public static int bestInvitation1(String[] first, String[] sencod) {
		int ans = 0;
		int i, j = 0;
		
		
		for(i=0; i < first.length; i++) {
			
			// 한명 망 기준 카운트 이기 때문에 i for 문에서 초기화를 해준다.
			int f = 0;
			int s = 0;
			
			for(j=0; j < first.length; j++) {
				
				
				if(first[i].equals(first[j])) f++;
				if(first[i].equals(sencod[j])) f++;
				if(sencod[i].equals(first[j])) s++;
				if(sencod[i].equals(sencod[j])) s++;
				
				ans = Math.max(ans, f);
				ans = Math.max(ans, s);
				
			}
			
		}
		
		return ans;
	}
	
	
	public static int bestInvitation2(String[] first, String[] sencod) {
		
		int ans = 0;
		
		Map<String, Integer> dic = new HashMap<String, Integer>();
		
		// 맵에 키를 저장
		for(int i=0; i < first.length; i++) {
			dic.put(first[i], 0);
			dic.put(sencod[i], 0);
		}
		
		
		// 배열에을 다시 돌면서 맵의 카운트를 추가함
		for(int i=0; i < first.length; i++) {
			dic.put(first[i], dic.get(first[i]) + 1);
			dic.put(sencod[i], dic.get(sencod[i]) + 1);
		}
		
		
		// 맵키중 가장 max 값을 반환함
		for(String key : dic.keySet()) {
			ans = Math.max(ans, dic.get(key));
		}
		
		
		return ans;
	}

}

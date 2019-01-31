package algorithm.topcoder;

import java.util.Arrays;

public class Cryptography {
	
	public static void main(String[] args) {
		System.out.println(encrypt1(new int[] {1,2,3}));
		System.out.println(encrypt1(new int[] {1,3,2,1,1,3}));
		
		System.out.println(encrypt2(new int[] {1,2,3}));
		System.out.println(encrypt2(new int[] {1,3,2,1,1,3}));
	}
	
	
	public static long encrypt1(int[] numbers) {
		
		long ans = 0;
		for(int i=0; i < numbers.length; i++) {
			
			
			long temp = 1;
			for(int j=0; j < numbers.length; j++) {
				
				if(i == j)
					temp *= numbers[j] + 1;
				else 
					temp *= numbers[j];
				
				ans = Math.max(ans, temp);
			}
		}
		
		return ans;
	}
	
	
	public static long encrypt2(int[] numbers) {
		int ret = 1;
		Arrays.sort(numbers);
		numbers[0] ++;
		
		for(int i=0; i < numbers.length; i++) {
			ret *= numbers[i];
		}
		
		return ret;
	}

}

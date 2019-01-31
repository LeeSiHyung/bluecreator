package algorithm2.chap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterestingDigits {
	
	public static void main(String[] args) {
		
		System.out.println(Arrays.toString(digits1(10)));
		System.out.println(Arrays.toString(digits1(3)));
		
		System.out.println(Arrays.toString(digits2(10)));
		System.out.println(Arrays.toString(digits2(3)));
	}
	
	public static int[] digits1(int base) {
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(int n=2; n < base; n++) {
			boolean chk = true;
			
			for(int k1=0; k1 < base; k1++) {
				for(int k2=0; k2 < base; k2++) {
					for(int k3=0; k3 < base; k3++) {
						// N의 배수이면서, 각 자리의  합이 N의 배수가 아닌 경우 break;
						if((k1 + (k2*base) + (k3*base*base)) % n == 0 && (k1+k2+k3) % n != 0 ) {
							chk = false;
							break;
						}
					}
					if(!chk) break;
				}
				if(!chk) break;
			}
			if(chk) list.add(n);
		}
		
		int[] ans = new int[list.size()];
		for(int i=0; i < list.size(); i++) {
			ans[i] = list.get(i);
		}
		
		return ans;
		
	}
	
	
	public static int[] digits2(int base) {
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(int n=2; n < base; n++) {
			if((base - 1) % n == 0)
				list.add(n);
		}
		
		
		int[] ans = new int[list.size()];
		for(int i=0; i < list.size(); i++) {
			ans[i] = list.get(i);
		}
		
		return ans;
		
	}

}

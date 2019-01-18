package algorithm2.chap01;

import java.util.Arrays;

public class KiwiJuiceEasy {
	
	public static void main(String[] args) {
		
		System.out.println(Arrays.toString(thePouring1(new int[] {20,20}, new int[] {5,8}, new int[] {0}, new int[] {1})));
		System.out.println(Arrays.toString(thePouring1(new int[] {10,10}, new int[] {5,8}, new int[] {0}, new int[] {1})));
		System.out.println(Arrays.toString(thePouring1(new int[] {30,20,10}, new int[] {10,5,5}, new int[] {0, 1, 2}, new int[] {1, 2, 0})));
		System.out.println(Arrays.toString(thePouring1(new int[] {14,35,86,58,25,62}, new int[] {6,34,27,38,9,60}, new int[] {1,2,4,5,3,3,1,0}, new int[] {0,1,2,4,2,5,3,1})));
		
		System.out.println();
		
		System.out.println(Arrays.toString(thePouring2(new int[] {20,20}, new int[] {5,8}, new int[] {0}, new int[] {1})));
		System.out.println(Arrays.toString(thePouring2(new int[] {10,10}, new int[] {5,8}, new int[] {0}, new int[] {1})));
		System.out.println(Arrays.toString(thePouring2(new int[] {30,20,10}, new int[] {10,5,5}, new int[] {0, 1, 2}, new int[] {1, 2, 0})));
		System.out.println(Arrays.toString(thePouring2(new int[] {14,35,86,58,25,62}, new int[] {6,34,27,38,9,60}, new int[] {1,2,4,5,3,3,1,0}, new int[] {0,1,2,4,2,5,3,1})));

	}
	
	/** 
	   	capacities : {20, 20}
		bottles : {5, 8}
		fromId : {0}
		toId : {1}
		returns : {0, 13}
		
	 **/

	/** 
	   	capacities : {10, 10}
		bottles : {5, 8}
		fromId : {0}
		toId : {1}
		returns : {3, 10}
		
	 **/
	
	public static int[] thePouring1(int[] capacities, int[] bottles, int[] fromId, int[] toId) {
		
		for(int i=0; i < fromId.length; i++) {
			int f = fromId[i];
			int t = toId[i];
			int min = Math.min(bottles[f], capacities[t] - bottles[t]);
			
			bottles[f] -= min; 
			bottles[t] += min;
		}
		
		return bottles;
	}
	
	
	public static int[] thePouring2(int[] capacities, int[] bottles, int[] fromId, int[] toId) {
		
		for(int i=0; i < fromId.length; i++) {
			int sum = bottles[fromId[i]] + bottles[toId[i]];
			bottles[toId[i]] = Math.min(sum, capacities[toId[i]]);
			bottles[fromId[i]] = sum - bottles[toId[i]];
		}
		
		return bottles;
	}

}

package algorithm.kakao1;

public class MainApp {
	
	public static void main(String[] args) {
		
		int n = 5;
		int [] arr1 = {9, 20, 28, 18, 11};
		int [] arr2 = {30, 1, 21, 17, 28};

		// "#####"
		// "# # #"
		// "### #"
		// "#  ##"
		// "#####"
		
		String[] result = new String[n];
		
		// for(int i=0; i < n; i++) {
		// 	System.out.println(String.format("%05d", new Integer(Integer.toBinaryString(arr1[i]))));
		// }
		// 
		// System.out.println();
		// 
		// for(int i=0; i < n; i++) {
		// 	System.out.println(String.format("%05d", new Integer(Integer.toBinaryString(arr1[i]))).replaceAll("1", "#").replaceAll("0", " "));
		// }
		// 
		// System.out.println();
		// 
		// for(int i=0; i < n; i++) {
		// 	System.out.println(String.format("%05d", new Integer(Integer.toBinaryString(arr2[i]))));
		// }
		// 
		// System.out.println();
		// 
		// for(int i=0; i < n; i++) {
		// 	System.out.println(String.format("%05d", new Integer(Integer.toBinaryString(arr2[i]))).replaceAll("1", "#").replaceAll("0", " "));
		// }
		// 
		// 
		// System.out.println();
		// 
		// for(int i=0; i < n; i++) {
		// 	System.out.println(String.format("%05d", new Integer(Integer.toBinaryString(arr1[i] | arr2[i]))));
		// }
		// 
		// System.out.println();
		// 
		// for(int i=0; i < n; i++) {
		// 	System.out.println(String.format("%05d", new Integer(Integer.toBinaryString(arr1[i] | arr2[i]))).replaceAll("1", "#").replaceAll("0", " "));
		// }
		
		for(int i=0; i < n; i++) {
			int compare = 1;
			String resultString = "";
			for(int idx=0; idx < n; idx++) {
				resultString = (((arr1[i] | arr2[i]) & compare) > 0 ? "#" : " ") + resultString;
				// 16 8 4 2 1 순으로 & 처리함
				compare = compare << 1;
			}
			System.out.println(resultString);
		}
	}
	
}

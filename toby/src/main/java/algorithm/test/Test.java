package algorithm.test;

public class Test {

	public static void main(String[] args) {

		// System.out.println(Math.sqrt(9));
		
		int[][] arr= {
				{3 , 2 , -3},
				{1 , 13 , 6},
				{15 , 23 , 23},
		};

		int len = arr.length;
		int x = 0;
		int y = 0;
		for (int i = 0; i < arr.length; i++) {
			int j = 0;
			for (j = 0; j < arr.length; j++) {
				if (i == j) {
					//System.out.println(arr[i][j]);
					System.out.println(arr[i][i - j + (len - 1)]);
					x += arr[i][j];
					y += arr[i][i - j + len];
				}
			}
		}
		//System.out.println(Math.abs(x - y)); ;
	}
}

package algorithm2.chap05;

public class QueenBB {
	
	static int[] pos = new int[8];
	static boolean[] flag = new boolean[8];
	
	static void print() {
		for(int i=0; i < 8; i++)
			System.out.printf("%2d", pos[i]);
		System.out.println();
	}
	
	static void set(int i) {
		for(int j=0; j < 8; j++) {
			 // 0 1 2 3 4 5 6 7
			 // 0 1 2 3 4 5 7 6
			 // 0 1 2 3 4 6 5 7
			if(flag[j] == false) {
				pos[i] = j;
				if(i == 7)
					print();
				else {
					flag[j] = true;
					set(i+1);
					// 재귀순환 set(i+1)은 I가 0, 1, 2, 3, 4, 5, 6, 7 증가시
					// I 7가 되면 J 0000000, 0000001, 0000002 ... 0000007
					// I 6가 되면 I 6->7 순으로 다시 돔. 0000010, 0000011, 0000012 ... 0000077
					// I 5가 되면 I 5->6->7 순으로 다시 돔. 0000100, 0000101, 0000102 ... 0000777
					// I 4가 되면 I 4->5->6->7 순으로 다시 돔.
					// I 3가 되면 I 3->4->5->6->7 순으로 다시 돔.
					// .......
					flag[j] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		set(0);
	}
}

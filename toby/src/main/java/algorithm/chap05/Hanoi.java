package algorithm.chap05;

import java.util.Scanner;

public class Hanoi {
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("하노이의 탑");
		System.out.print("원반의 개수 : ");
		
		int n = stdIn.nextInt();
		
		move(n, 1, 3);
		
	}

	private static void move(int no, int x, int y) {
		if(no > 1) {
			// 6-x-y=> 5-y => 2오면 3으로 변경, 3이오면 2로 변경
			move(no-1, x, 6 -x -y);
		}
		
		System.out.println("원반[" + no + "]을 " + x + " 기둥에서 " + y + "기둥으로 옮김");
		
		if(no > 1) {
			move(no-1, 6 -x -y, y);
		}
	}

}

package algorithm2.chap05;

import java.util.Scanner;

public class Hanoi2 {
	
	// static void move(int no, int x, int y) {
	// 	if(no > 1) move(no-1, x, 6-x-y);
	// 	System.out.println("원반 " + no + "가 " + x + "에서" + y + "로 이동했습니다.");
	// 	if(no > 1) move(no-1, 6-x-y, y);
	// }
	
	static void move(int no, int x, int y) {
		
		if(no == 1) {
			System.out.println("원반 " + no + "가 " + x + "에서" + y + "로 이동했습니다.");
		}
		else {
			move(no-1, x, 6-x-y);
			System.out.println("원반 " + no + "가 " + x + "에서" + y + "로 이동했습니다.");
			move(no-1, 6-x-y, y);
		}
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		System.out.print("하노이탑 원반 갯수 : ");
		int n = stdIn.nextInt();
		move(n, 1, 3);
	}
	

}

package algorithm2.chap05;

import java.util.Scanner;

public class Hanoi {
	
	static void move(int no, int x, int y) {
		if(no == 1) {
			System.out.println("원반 " + no + "를 " + x + "에서 " + y + "로 옮겼습니다.");
		}
		else{
			move(no - 1, x, 6-x-y);
			System.out.println("원반 " + no + "를 " + x + "에서 " + y + "로 옮겼습니다.");
			move(no - 1, 6-x-y, y);
		}
	}
	
	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		System.out.println("하노이의 탑");
		System.out.print("원반 개수 : ");
		int n = stdIn.nextInt();
		
		move(n, 1, 3);
		
	}

}

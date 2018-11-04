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
		if(no == 1) {
			// 마지막 원반이 옮겨지고 난 후 프린트를 한다.
			System.out.println("원반[" + no + "]을 " + x + " 기둥에서 " + y + "기둥으로 옮김");
		}
		else {
			// no = 4 x = 1, y = 2 즉 1번 기둥에서 2번 기둥으로 옮기기 (재귀함수를 통해 x, y가 계속 변경하기 때문에 6-x-y를 해줘야 중간 값이 설정됨)
			move(no-1, x, 6 -x -y);
			
			// 1번씩 옮겨지기 때문에 옮긴 횟수별로 프린트를 찍어야 한다.목표 기둥에 이동하는 경우에만 위의 프린트가 남기 때문에 이곳에 프린트를 해준다.
			System.out.println("원반[" + no + "]을 " + x + " 기둥에서 " + y + "기둥으로 옮김");
			
			// 중간에 있는 no -1 의 원반을 목표 기둥으로 옮긴다.
			move(no-1, 6 -x -y, y);
		}
	}
	
	//  private static void move(int no, int x, int y) {
	//  	if(no > 1) {
	//  		System.out.println("1번 함수 : " + no);
	//  		// 6-x-y=> 5-y => 2오면 3으로 변경, 3이오면 2로 변경
	//  		move(no-1, x, 6 -x -y);
	//  	}
	//  	
	//  	System.out.println("원반[" + no + "]을 " + x + " 기둥에서 " + y + "기둥으로 옮김");
	//  	
	//  	if(no > 1) {
	//  		System.out.println("2번 함수 : " + no);
	//  		move(no-1, 6 -x -y, y);
	//  	}
	//  }

}

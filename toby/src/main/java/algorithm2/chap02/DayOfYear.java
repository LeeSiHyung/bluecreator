package algorithm2.chap02;

import java.util.Scanner;

public class DayOfYear {
	
	static int[][] mdays = {
		{31,28,31,30,31,30,31,31,30,31,30,31},
		{31,29,31,30,31,30,31,31,30,31,30,31},
	};
	
	static int isLeap(int year) {
		return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 1 : 0;
	}
	
	static int dayOfYear(int y, int m, int d) {
		int days = d;
		for(int i=1; i < m; i++) {
			days += mdays[isLeap(y)][i-1];
		}
		return days;
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int retry;
		
		do {
			System.out.print("년 : "); int y = stdIn.nextInt();
			System.out.print("월 : "); int m = stdIn.nextInt();
			System.out.print("일 : "); int d = stdIn.nextInt();
			
			System.out.printf("그 해 %d일째입니다. \n", dayOfYear(y,m,d));
			
			System.out.print("한번 더 할까요?(1.예/2.아니오) : ");
			retry = stdIn.nextInt();
		}
		while(retry == 1);
	}

}

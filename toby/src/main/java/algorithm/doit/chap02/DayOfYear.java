package algorithm.doit.chap02;

public class DayOfYear {
	
	static int[][] mdays = {
			{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, // 평년
			{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, // 윤년
	};
	
	static int isLeap(int year) {
		// 4로 나누어 떨어지고 100으로 나누어 떨어지지 않으며 400으로 나누어 떨어지는 해를 윤년
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
		System.out.println(dayOfYear(2019,3,15));
	}

}

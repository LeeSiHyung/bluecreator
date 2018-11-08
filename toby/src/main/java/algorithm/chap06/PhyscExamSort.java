package algorithm.chap06;

import java.util.Arrays;
import java.util.Comparator;

public class PhyscExamSort {
	
	static class PhyscData{
		String name;
		int height;
		double vision;
		
		public PhyscData(String name, int height, double vision) {
			this.name = name;
			this.height = height;
			this.vision = vision;
		}

		@Override
		public String toString() {
			return name + " " + height + " " + vision;
		}
		
		// static final Comparator<PhyscData> HEIGHT_ORDER = new Comparator<PhyscData>() {
		// 	@Override
		// 	public int compare(PhyscData o1, PhyscData o2) {
		// 		return (o1.height > o2.height) ? 1: (o1.height < o2.height) ? -1 : 0;
		// 	}
		// };
		
		static final Comparator<PhyscData> HEIGHT_ORDER = new heightOrderComparator();
		
		private static class heightOrderComparator implements Comparator<PhyscData>{
			public int compare(PhyscData o1, PhyscData o2) {
				return (o1.height > o2.height) ? 1: (o1.height < o2.height) ? -1 : 0;
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		PhyscData[] x = {
			new PhyscData("이나령", 162, 0.3),
			new PhyscData("전서현", 173, 0.7),
			new PhyscData("이수민", 175, 2.0),
			new PhyscData("홍준기", 171, 1.5),
			new PhyscData("유지훈", 168, 0.4),
			new PhyscData("이호연", 174, 1.2),
			new PhyscData("김한결", 169, 0.8),
		};
		
		Arrays.sort(x, PhyscData.HEIGHT_ORDER);
		
		System.out.println("신체검사 리스트");
		System.out.println("이름     키      시력");
		System.out.println("-------------");
		for(int i=0; i < x.length; i++) {
			System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
		}
	}

}

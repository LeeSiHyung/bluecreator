package test.if_;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MainTest {
	
	public static void main(String[] args) {
		// 인터페이스에 정의한 변수는 자동으로 public static final이 된다.
		System.out.println(SwingConstants.EAST);
		
		
		String[] friends = {"Peter", "Paul", "Mary"};
		Arrays.sort(friends);
		for(String s : friends) {
			System.out.printf("%s ", s);
		}
		System.out.println();
		
		// 인터페이스인 Runnable을 구현하고 run 메소드를 작성하는 것을 아래 처럼 간단하게 처리함.
		class HelloTask implements Runnable{
			@Override
			public void run() {
				for(int i=0; i < 2; i++) {
					System.out.println("스레드1");
				}
			}
		}
		Runnable task1 = new HelloTask();
		Thread thread = new Thread(task1);
		thread.start();
		
		// 람다식으로 간단하게 작성
		Runnable task2 = () -> {
			for(int i=0; i < 2; i++) {
				System.out.println("스레드2");
			}
		};
		Thread thread2 = new Thread(task2);
		thread2.start();
		
		
		class LengthComparator implements Comparator<String>{
			@Override
			public int compare(String first, String second) {
				return first.length() - second.length();
			}
		}
		
		// 위의 로직을 아래와 같이 간단하게 처리함.
		Comparator<String> comp = (first, second) -> first.length() - second.length();
		
		
		String[] friends2 = {"Peter", "Paul", "Mary"};
		// Comparator를 구현해야 하는 것을 아래와 같이 람다식을 이용해 간단하게 작성할 수 있다.
		Arrays.sort(friends2, (first, second) -> first.length() - second.length());
		for(String s : friends2) {
			System.out.printf("%s ", s);
		}
		System.out.println();
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		a.removeIf(p -> p == 1);
		System.err.println(a);
		

	}
}

package test.exception_;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class HandlingUncaughtExceptionsForThreads {
	public static void main(String[] args) {
		
		System.out.println(etc(null));
		
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("["+ currentThreadName + "]" + " thread starts here...");

		try {
			
			// 예외 발생 감지를 걸어야 함.
			Thread.setDefaultUncaughtExceptionHandler((thread, ex) -> {
				
				// 예외의 스택 추적 내용을 저장하고 싶을 때
				try(ByteArrayOutputStream out = new ByteArrayOutputStream();
						PrintWriter writer = new PrintWriter(out);
						){
					
					ex.printStackTrace(writer);
					String description = out.toString();
					System.out.println("예외 발생 감지? " + description );

				} catch (IOException e) {
					e.printStackTrace();
				}
				
			});
			
			new Thread(new ExceptionLeakingTask(), "Mythread-1").start();
			
			
		// 스레드로 생성된 Exception은 감지가 안된다.
		} catch(RuntimeException re) {
			System.out.println("!!!!!!["+ currentThreadName + "]" + " Caught Exception!!!");
		}

		System.out.println("["+ currentThreadName + "]" + " thread ends here...");
		
	}
	
	static String etc(String direction) {
		return Objects.requireNonNull(direction, "값이 에러가 발생했는지 체크");
	}
	
	static class ExceptionLeakingTask implements Runnable {

		@Override
		public void run() {
			throw new RuntimeException();
		}

	}
}

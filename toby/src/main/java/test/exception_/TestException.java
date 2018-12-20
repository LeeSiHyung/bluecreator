package test.exception_;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TestException {

	public static void main(String[] args) {
		
		try {
			int x = 21 / 0;
		}
		catch (NumberFormatException | NullPointerException e) {
			// TODO: handle exception
		}
		
		
		ArrayList<String> lines = new ArrayList<String>();
		try(Scanner in = new Scanner(System.in); PrintWriter out = new PrintWriter("test.txt")){
			while(in.hasNext())
				out.println(in.next().toLowerCase());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// 주 예외인 FileNotFoundException exception을 잡고, 부 예외를 getSuppressed()로 추출한다.
			Throwable[] secondaryExceptions = e.getSuppressed();
			
			// 만약 이전에 예외가 감싸진채로 온것이라면,이런식으로 getCause 메소드를 통해 원본 예외를 추출할 수 있다.
			Throwable cause = e.getCause();
			
			// 위의 처리가 가능하려면 아래와 같이 initCause 메소드로 꼭 원본 예외를 저장해야 한다.
			Throwable ex2 = new Throwable();
			ex2.initCause(e);
		}
	}
}

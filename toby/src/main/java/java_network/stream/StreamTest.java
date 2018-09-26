package java_network.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamTest {
	
	public static void generateCharacters(OutputStream out) throws IOException {
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharacterPerLine = 72;
		
		int start = firstPrintableCharacter;
		while(true) {
			for(int i = start; i < start + numberOfCharacterPerLine; i++) {
				out.write(((i - firstPrintableCharacter) % numberOfPrintableCharacters)  + firstPrintableCharacter);
			}
			out.write('\r'); // 캐리지 리턴
			out.write('\n'); // 라인피드
			
			start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
		}
		
	}
	
	public static void generateCharacters2(OutputStream out) throws IOException{
		
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharactersPerLine = 72;
		
		int start = firstPrintableCharacter;
		byte[] line = new byte[numberOfCharactersPerLine + 2];
		// + 2는 캐리지리턴과 라인피드를 위함이다.
		
		while(true) {
			for(int i = start; i < start + numberOfCharactersPerLine; i++) {
				// 33 ~ 104
				line[i-start] = (byte) ((i - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter); 
			}
			
			line[72] = (byte) 'r';
			line[73] = (byte) 'n';
			
			out.write(line);
			
			// 34 ~ 105
			start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
			
			// 33에서 ~ (33 + 94 - 1) 즉 126이 될 때까지 반복한다. 
		}
		
		
		
	}
	
	
	// 인스턴스 해체 패턴
	public void fileOutputStream() {
		OutputStream out = null;
		
		try {
			out = new FileOutputStream("/tmp/data.txt");
			
		}
		catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		finally {
			if(out != null) {
				try {
					out.close();
				}catch (IOException ex2) {
					// 무시한다.
				}
			}
		}
	}
	
	
	// try-with-resources
	public void fileOutputStream2() {
		// AutoCloseable 객체
		try(OutputStream out = new FileOutputStream("/tmp/data.txt")){
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void inputStream() {
		
		try(InputStream in = new FileInputStream("/tmp/input.txt")) {
			
			byte[] input = new byte[10];
			for(int i=0; i < input.length; i++){
				int b = in.read();
				if(b == -1) break;
				input[i] = (byte) b;
			}
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	
	public void inputStream2() {
		try(InputStream in = new FileInputStream("/tmp/input.txt")){
			
			int bytesRead = 0;
			int bytesToRead = 1024;
			byte[] input = new byte[bytesToRead];
			while(bytesRead < bytesToRead) {
				int result = in.read(input, bytesRead, bytesToRead - bytesRead);
				if(result == -1) break;
				bytesRead += result;
			}
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void inputStream3() {
		try(InputStream in = new FileInputStream("/tmp/input.txt")){
			
			int bytesAvailable = in.available();
			byte[] input = new byte[bytesAvailable];
			
			int bytesRead = in.read(input, 0, bytesAvailable);
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	

	public static void main(String[] args) {

		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharacterPerLine = 72;
		
		
		int start = firstPrintableCharacter;
		for(int i = start; i < start + numberOfCharacterPerLine; i++) {
			// System.out.print(i + "-");
			// System.out.print((i - firstPrintableCharacter) + "-");
			// System.out.print((i - firstPrintableCharacter) % numberOfPrintableCharacters + "-");
			System.out.print(((i - firstPrintableCharacter) % numberOfPrintableCharacters) + firstPrintableCharacter + "-");
			
			// System.out.print((start + 1) - firstPrintableCharacter + "-");
			start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
			System.out.println(start);
		}
		
	}
	
}


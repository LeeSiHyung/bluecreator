package java_network.stream;

import java.io.IOException;
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

	public static void main(String[] args) {

		// System.out.println(33 % 94);
		// System.out.println(0 % 94);
		// System.out.println(66 % 94);
		// System.out.println((94 * 2 + 93) % 94);
		// System.out.println(94 - (33 * 2));
		
		int firstPrintableCharacter = 33;
		int numberOfPrintableCharacters = 94;
		int numberOfCharacterPerLine = 72;
		
		int start = firstPrintableCharacter;
		for(int i = start; i < start + numberOfCharacterPerLine; i++) {
			// System.out.print(i + "-");
			// System.out.print((i - firstPrintableCharacter) + "-");
			System.out.print((i - firstPrintableCharacter) % numberOfPrintableCharacters + "-");
			// System.out.println(((i - firstPrintableCharacter) % numberOfPrintableCharacters) + firstPrintableCharacter);
			
			System.out.print((start + 1) - firstPrintableCharacter + "-");
			start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
			System.out.println(start);
		}
		
	}
	
}


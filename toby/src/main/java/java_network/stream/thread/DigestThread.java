package java_network.stream.thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class DigestThread extends Thread{

	String filename;

	public DigestThread(String filename) {
		this.filename = filename;
	}

	@Override
	public void run() {
		
		try(InputStream in = new FileInputStream(filename)){
			
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);
			while(din.read() != -1);
			din.close();
			
			byte[] digest = sha.digest();
			StringBuilder result = new StringBuilder(filename);
			result.append(": ");
			result.append(DatatypeConverter.printHexBinary(digest));
			System.out.println(result);
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		for(String filename: args) {
			Thread t = new DigestThread(filename);
			t.start();
		}
	}
	
}

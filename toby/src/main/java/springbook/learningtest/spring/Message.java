package springbook.learningtest.spring;

public class Message {

	String text;
	
	public static Message newMessage(String text){
		return new Message(text);
	}

	public Message(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
}

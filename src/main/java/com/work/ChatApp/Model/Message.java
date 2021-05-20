package com.work.ChatApp.Model;

public class Message {

	private String sender;
	private String type;
	private String message;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [sender=" + sender + ", type=" + type + ", message=" + message + "]";
	}

	public Message(String sender, String type, String message) {
		super();
		this.sender = sender;
		this.type = type;
		this.message = message;
	}

	public Message() {
		super();
	}
	
}

package it.eng.idsa.model.message;

import java.util.Vector;

public class MessagesIDS {
	
	private static int i=0; 
	private static MessagesIDS messagesIDS = null;
	private Vector<MessageIDS> messages;
	
	private MessagesIDS() {
		this.messages = new Vector<MessageIDS>();
	}
	
	public static MessagesIDS getInstance() {
		if(messagesIDS == null) {
			messagesIDS = new MessagesIDS();
		}
		return messagesIDS; 
	}

	public Vector<MessageIDS> getMessages() {
		return this.messages;
	}

	public void setMessages(Vector<MessageIDS> messages) {
		this.messages = messages;
	}
	
	public void putMessage(String msg) {
		i++;
		MessageIDS messageIDS = new MessageIDS();
		messageIDS.setId(i);
		messageIDS.setText(msg);
		this.getMessages().add(messageIDS);
	}
	
}

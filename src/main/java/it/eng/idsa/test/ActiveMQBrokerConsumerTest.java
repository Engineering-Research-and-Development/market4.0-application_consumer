package it.eng.idsa.test;

import javax.jms.JMSException;

import it.eng.idsa.QueueMessageConsumer;

public class ActiveMQBrokerConsumerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String brokerUrl = "tcp://192.168.56.103:61816";
		//brokerUrl="ssl://192.168.56.103:61713";

		QueueMessageConsumer queueMsgListener = new QueueMessageConsumer(brokerUrl, "admin", "admin");
		queueMsgListener.setDestinationName("milan");

		try {
			queueMsgListener.run();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

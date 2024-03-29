package it.eng.idsa;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import it.eng.idsa.model.message.MessagesIDS;
import it.eng.idsa.util.PropertiesConfig;
/**
 * The QueueMessageConsumer consumes messages to ActiveMQ Broker
 * 
 * @author  Gabriele De Luca, Milan Karajovic
 */
public class QueueMessageConsumer implements MessageListener {
	private static final PropertiesConfig CONFIG_PROPERTIES = PropertiesConfig.getInstance();
	private static final String CONSUMER_MESSAGE_RECEIVED = "QueueMessageConsumer Received message [ %s ]";

	private static Logger LOG = Logger.getLogger(QueueMessageConsumer.class.getName());

    private String activeMqBrokerUri;
    private String username;
    private String password;
    private String destinationName;

    public QueueMessageConsumer(String activeMqBrokerUri, String username, String password) {
        super();
        this.activeMqBrokerUri = activeMqBrokerUri;
        this.username = username;
        this.password = password;
    }

    public void run() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(username, password, activeMqBrokerUri);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(destinationName);
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(this);

       LOG.debug(String.format("QueueMessageConsumer Waiting for messages at %s %s",
                destinationName, this.activeMqBrokerUri));
    }

    public void onMessage(Message message) {
        String msg;
        try {
            msg = String.format(CONSUMER_MESSAGE_RECEIVED,
                    ((TextMessage) message).getText());
            //Thread.sleep(1000);// sleep for 10 seconds
            System.out.println(msg);
            senReceivedMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void senReceivedMessage(String msg) {
    	MessagesIDS.getInstance().putMessage(msg);
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }
}

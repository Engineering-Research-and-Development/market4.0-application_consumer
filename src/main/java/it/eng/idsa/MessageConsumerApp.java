package it.eng.idsa;

import javax.jms.JMSException;

import org.apache.log4j.Logger;

import it.eng.idsa.util.DemoDataUtils;



public class MessageConsumerApp {
	private static Logger LOG = Logger.getLogger(MessageConsumerApp.class.getName());

    public static void main(String[] args) {

        String brokerUrl = DemoDataUtils.readBrokerURL();

        QueueMessageConsumer queueMsgListener = new QueueMessageConsumer(brokerUrl, "admin", "admin");
        queueMsgListener.setDestinationName(DemoDataUtils.DESTINATION);

        try {
            queueMsgListener.run();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void activateListening() {
    	 String brokerUrl = DemoDataUtils.readBrokerURL();

         QueueMessageConsumer queueMsgListener = new QueueMessageConsumer(brokerUrl, "admin", "admin");
         queueMsgListener.setDestinationName(DemoDataUtils.DESTINATION);

         try {
             queueMsgListener.run();
         } catch (JMSException e) {
             e.printStackTrace();
         }
    }
}

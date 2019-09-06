package it.eng.idsa;

import javax.jms.JMSException;

import org.apache.log4j.Logger;

import it.eng.idsa.util.DemoDataUtils;



/**
* The MessageConsumerApp class is responsible to activate/deactivate ActiveMQ queue listeners
*
* @author  Gabriele De Luca, Milan Karajovic
*/
public class MessageConsumerApp {
	private static Logger LOG = Logger.getLogger(MessageConsumerApp.class.getName());

    public void activateListening() {
    	 String brokerUrl = DemoDataUtils.readBrokerURL();

         QueueMessageConsumer queueMsgListener = new QueueMessageConsumer(brokerUrl, "admin", "admin");
         queueMsgListener.setDestinationName(DemoDataUtils.DESTINATION);

         try {
        	 LOG.debug("Run queue listening...");
             queueMsgListener.run();
         } catch (JMSException e) {
             e.printStackTrace();
         }
    }
}

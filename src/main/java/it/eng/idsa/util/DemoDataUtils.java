package it.eng.idsa.util;

import it.eng.idsa.util.PropertiesConfig;
/**
 * The DemoDataUtils class is a data utility class
 * 
 * 
 * @author  Gabriele De Luca, Milan Karajovic
 */
public final class DemoDataUtils {

    private static final PropertiesConfig CONFIG_PROPERTIES = PropertiesConfig.getInstance();

	public static final String DESTINATION = "test.queue";

    private static final String BROKER_SETUP = "failover:(";

    private static final String HALF_MINUTE_TIMEOUT = ")?timeout=30000";

    private static String getBrokerURI(String uri) {
        StringBuffer brokerUrl = new StringBuffer(BROKER_SETUP);
        brokerUrl.append(uri);
        brokerUrl.append(HALF_MINUTE_TIMEOUT);
        return brokerUrl.toString();
    }

    public static String readBrokerURL() {
        String brokerUrl = null;        
        
        String brokerOpenwirelUrl = DemoDataUtils.getBrokerURI(CONFIG_PROPERTIES.getProperty("brokerOpenwirelUrl"));
        brokerUrl = DemoDataUtils.getBrokerURI(brokerOpenwirelUrl);

        return brokerUrl;
    }

}

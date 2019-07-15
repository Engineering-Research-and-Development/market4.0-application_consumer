package it.eng.idsa.util;

import it.eng.idsa.util.PropertiesConfig;
/**
 * The constant data utility used in this Demo
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
        String brokerSSLUrl = DemoDataUtils.getBrokerURI(CONFIG_PROPERTIES.getProperty("brokerSslUrl"));
        brokerUrl = DemoDataUtils.getBrokerURI(brokerSSLUrl);

        return brokerUrl;
    }

}

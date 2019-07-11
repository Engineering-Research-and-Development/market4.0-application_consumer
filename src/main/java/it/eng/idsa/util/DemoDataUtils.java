package it.eng.idsa.util;
/**
 * The constant data utility used in this Demo
 */
public final class DemoDataUtils {

    public static final String DESTINATION = "test.queue";

    private static final String BROKER_SETUP = "failover:(";

    private static final String HALF_MINUTE_TIMEOUT = ")?timeout=30000";

    private static String BROKER_SSL = "ssl://192.168.56.102:61714";

    private static String getBrokerURI(String uri) {
        StringBuffer brokerUrl = new StringBuffer(BROKER_SETUP);
        brokerUrl.append(uri);
        brokerUrl.append(HALF_MINUTE_TIMEOUT);
        return brokerUrl.toString();
    }

    public static String readBrokerURL() {
        String brokerUrl = null;
        brokerUrl = DemoDataUtils.getBrokerURI(BROKER_SSL);

        return brokerUrl;
    }

}

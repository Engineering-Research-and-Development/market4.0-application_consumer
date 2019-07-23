package it.eng.idsa;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

import it.eng.idsa.util.PropertiesConfig;



public class ConsumerConnection {
	private static final PropertiesConfig CONFIG_PROPERTIES = PropertiesConfig.getInstance();
	private static Logger LOG = Logger.getLogger(ConsumerConnection.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String token = null;
		try {
			LOG.debug("ConsumerConnector starting...");

			Path targetDirectory=Paths.get(CONFIG_PROPERTIES.getProperty("targetDirectory"));
			String dapsUrl=CONFIG_PROPERTIES.getProperty("dapsUrl");
			String keyStoreName=CONFIG_PROPERTIES.getProperty("keyStoreName");
			String keyStorePassword=CONFIG_PROPERTIES.getProperty("keyStorePassword");
			String keystoreAliasName=CONFIG_PROPERTIES.getProperty("keystoreAliasName");
			String connectorUUID=CONFIG_PROPERTIES.getProperty("connectorUUID");

			DAPSInteraction dapsInteraction=new DAPSInteraction();
			token=dapsInteraction.acquireToken(targetDirectory, dapsUrl, keyStoreName, keyStorePassword, keystoreAliasName, connectorUUID);

			LOG.debug("TOKEN="+token);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		ClientConfig config = new ClientConfig();
		config.connectorProvider(new ApacheConnectorProvider());


		/*
		 * config.property(ClientProperties.PROXY_URI, "proxy_url");
		 * config.property(ClientProperties.PROXY_USERNAME,"user_name");
		 * config.property(ClientProperties.PROXY_PASSWORD,"password");
		 */
		Client client = ClientBuilder.newClient(config);		
		
		WebTarget webTarget = client.target(CONFIG_PROPERTIES.getProperty("providerUri"));
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		 
		Response response = invocationBuilder.post(Entity.entity(token, MediaType.APPLICATION_JSON));
	
		System.out.println("response="+response.readEntity(String.class));
	
		if (response.getStatus()==Response.Status.UNAUTHORIZED.getStatusCode()) {
			LOG.debug("UNAUTHORIZED");
		}
		if (response.getStatus()==Response.Status.ACCEPTED.getStatusCode()) {
			LOG.debug("ACCEPTED");
			MessageConsumerApp messageConsumerApp=new MessageConsumerApp();
			messageConsumerApp.activateListening();
			LOG.debug("***Listening Activated***");
		}
	}
}

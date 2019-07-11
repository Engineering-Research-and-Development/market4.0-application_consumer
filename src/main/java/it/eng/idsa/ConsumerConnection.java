package it.eng.idsa;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

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



public class ConsumerConnection {
	private static Logger LOG = Logger.getLogger(ConsumerConnection.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String token = null;
		try {
			LOG.debug("ConsumerConnector starting...");
			FileInputStream input = new FileInputStream("config.properties");
			// load a properties file
			Properties prop = new Properties();
			prop.load(input);

			Path targetDirectory=Paths.get(prop.getProperty("targetDirectory"));
			String dapsUrl=prop.getProperty("dapsUrl");
			String keyStoreName=prop.getProperty("keyStoreName");
			String keyStorePassword=prop.getProperty("keyStorePassword");
			String keystoreAliasName=prop.getProperty("keystoreAliasName");
			String connectorUUID=prop.getProperty("connectorUUID");

			DAPSInteraction dapsInteraction=new DAPSInteraction();
			token=dapsInteraction.acquireToken(targetDirectory, dapsUrl, keyStoreName, keyStorePassword, keystoreAliasName, connectorUUID);
			token="test"; 
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
		
		WebTarget webTarget = client.target("http://localhost:8080/base-producer/webapi/provider");
		 
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

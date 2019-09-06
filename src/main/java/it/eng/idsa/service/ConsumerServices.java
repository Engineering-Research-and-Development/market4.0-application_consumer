package it.eng.idsa.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.eng.idsa.ConsumerConnection;
import it.eng.idsa.model.message.MessagesIDS;
import net.minidev.json.JSONObject;

@Path("consumer")
public class ConsumerServices {

	@GET
	@Path("/allMessages")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllMessages() throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String messagesJSON = om.writeValueAsString(MessagesIDS.getInstance().getMessages());
		
		return Response.ok().
				entity(messagesJSON).
				header("Access-Control-Allow-Origin", "*").
				build();
	}
	
	@GET
	@Path("/startConsumer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response startConsumer() throws JsonProcessingException {
		boolean consumerStarted = ConsumerConnection.getInstance().startConsumer();
		JSONObject json=new JSONObject();
	    json.put("consumerStarted",consumerStarted);
		
		return Response.ok().
				entity(json.toString()).
				header("Access-Control-Allow-Origin", "*").
				build(); 
	}
	
	@POST
	@Path("/receivedMessage")
	@Produces(MediaType.APPLICATION_JSON)
	public Response receivedMessage(String message) {
		MessagesIDS.getInstance().putMessage(message);
		
		JSONObject json=new JSONObject();
	    json.put("receivedMessage", message);
	    
		return Response.ok().
				entity(json.toString()).
				header("Access-Control-Allow-Origin", "*").
				build();
		
	}
}

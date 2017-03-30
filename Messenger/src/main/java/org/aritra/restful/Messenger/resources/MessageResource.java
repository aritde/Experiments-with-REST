package org.aritra.restful.Messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.aritra.restful.Messenger.model.Message;
import org.aritra.restful.Messenger.resources.beans.MessageFilterBean;
import org.aritra.restful.Messenger.service.MessageService;

@Path("/messages")
public class MessageResource {
	MessageService messageService = new MessageService();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year")int year,
	 								 @QueryParam("start")int start,
	 								 @QueryParam("size")int size)
	{
		if(year>0)
		{
			return messageService.getAllMessagesForYear(year);
		}
		if(start>=0 && size>=0)
		{
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message)
	{
		
		return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id,Message message)
	{
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id)
	{
		messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON )
	public Message getMessage(@PathParam("messageId") long id)// In place of long, you can have any datatype. The conversion is automatic
	{
		return messageService.getMessage(id);
		//return messageService.getMessage(Long.parseLong(id));// You need not do this conversion explitcitly. taken care by the long above
		//return "Got path param" + messageId ;
	}
	/* You could have done it here. But it is not a good practice to combine resources. 
	 * Instead, it is a good practice to branch out the actions associated with comments 
	 * into a sub resource
	 */
	// This is not required here because, all kinds of request, irrespective of the type
	// which has to deal with comments needs to be sent to be Comment Resource class 
	// Hence, we need to avoid @GET  
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource()
	{
		//In case you want, you cant access the message ID in comment resource.
		return new CommentResource();
	}
}
  
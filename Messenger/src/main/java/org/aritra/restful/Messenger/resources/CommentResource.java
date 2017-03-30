package org.aritra.restful.Messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.aritra.restful.Messenger.model.Comment;
import org.aritra.restful.Messenger.service.CommentService;

@Path("/")// The path here is / because it is referred to by the MessageResource class. So the /messages/{messageID}/comments is already handled there
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId)
	{
		return commentService.getAllComments(messageId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment)
	{
		return commentService.addComment(messageId, comment);
	}
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId,
								 @PathParam("commentId") long commentId,
								 Comment comment)
	{
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}
	@DELETE
	@Path("/commentId")
	public void deleteComment(@PathParam("messageId") long messageId,
							  @PathParam("commentId") long commentId)
	{
		commentService.removeComment(messageId, commentId);
		
	}
	
	@GET
	@Path("/commentId")
	public Comment getMessage(@PathParam("messageId") long messageId ,
							  @PathParam("commentId") long commentId)
	{
		return commentService.getComment(messageId, commentId);
	}
}

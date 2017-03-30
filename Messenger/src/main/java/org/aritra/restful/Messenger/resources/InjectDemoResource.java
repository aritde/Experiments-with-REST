package org.aritra.restful.Messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("CustomHeaderValue") String num,
											@CookieParam("name") String cookie)
	{
		// The only diff between matrix param and query param is that in matrix param, innstead of the ?, you have ;
		return "Matrix param : " + matrixParam + "Header Param : " + num + " Cookie : "+cookie;
	}
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo , @Context HttpHeaders header)
	{
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = header.getCookies().toString(); // Ideally returns a map 
		return "Absolute Path : " + path + " Cookies :" + cookies;
	}

}

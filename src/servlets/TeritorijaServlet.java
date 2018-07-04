package servlets;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.GrandContainer;
import beans.Teritorija;
import helpers.AuthorizationService;
import helpers.ErrorModel;

@Path("/api")
public class TeritorijaServlet {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("teritorije/{teritorijaId}")
	public Response findById(@PathParam("teritorijaId") long id, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		Teritorija t = gc.findTeritorija(id);
		if (t == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Cannot find specified teritorija.")).build();
		}
		
		return Response.status(Response.Status.OK).entity(t).build();
	}
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("teritorije")
	public Response index(@Context HttpServletRequest request){
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");

		return Response.status(Response.Status.OK).entity(gc.getTeritorije()).build();
	}
	
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("teritorije")
	public Response create(@Context HttpServletRequest request, Teritorija ter) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isAdmin(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		try {
			ter.setId(gc.getNextTeritorijaID());
			gc.addTeritorija(ter);
			return Response.status(Response.Status.OK).entity(ter).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error occurred.")).build();
		}
	}
	
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("teritorije/{teritorijaId}")
	public Response update(@PathParam("teritorijaId") long id, @Context HttpServletRequest request, Teritorija ter) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isAdmin(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		try {
			Teritorija t = gc.findTeritorija(id);
			if (t != null) {
				gc.updateTeritorija(ter, id);
				return Response.status(Response.Status.OK).entity(t).build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Cannot find specified teritorija.")).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error occurred.")).build();
		}
	}
	
	
	@DELETE
	@Path("teritorije/{teritorijaId}")
	public Response delete(@PathParam("teritorijaId") long id, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isAdmin(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		Teritorija t = gc.findTeritorija(id);
		if (t != null) {
			gc.removeTeritorija(t);
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error occurred.")).build();
		}
	}
	
}

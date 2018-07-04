package servlets;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.GrandContainer;
import beans.Korisnik;
import helpers.ErrorModel;
import requests.SigninRequest;
import responses.KorisnikResponse;


@Path("api/auth")
public class AuthServlet {

	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("signin")
    public Response signin(@Context HttpServletRequest request, SigninRequest sr) {
		HttpSession session = request.getSession();
        Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
        
        if (loggedInUser != null) {
        	return Response.status(Response.Status.OK).entity(new KorisnikResponse(loggedInUser)).build();
        }
        
    	ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		Korisnik k =  gc.signin(sr.getUsername(), sr.getPassword());
		if (k != null) {
			session.setAttribute("user", k);
			return Response.status(Response.Status.OK).entity(new KorisnikResponse(k)).build();
		} else {
			return Response.status(Response.Status.FORBIDDEN).entity(new ErrorModel("Wrong credentials.")).build();
		}
    }
	
	@GET
	@Path("logout")
	public Response logout(@Context HttpServletRequest request) {
		request.getSession().invalidate();
		return Response.status(Response.Status.OK).build();
	}
	
	
	@GET
	@Path("user")
	public Response getUser(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
        Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
        
        if (loggedInUser != null) {
        	return Response.status(Response.Status.OK).entity(new KorisnikResponse(loggedInUser)).build();
        }
        
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("User not logged in.")).build();
	}
}

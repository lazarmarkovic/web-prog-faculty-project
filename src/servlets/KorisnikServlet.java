package servlets;

import java.util.ArrayList;

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
import beans.Korisnik;
import beans.Teritorija;
import beans.enums.StanjeVolontera;
import beans.enums.Vrsta;
import helpers.AuthorizationService;
import helpers.ErrorModel;
import requests.KorisnikCreateRequest;
import requests.UpdateAvatarRequest;
import responses.KorisnikResponse;


@Path("/api")
public class KorisnikServlet {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("korisnici/{kime}")
	public Response findById(@PathParam("kime") String kime, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isLog(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		try {
		
			Korisnik k = gc.findKorisnik(kime);
			if (k == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Cannot find specified korisnik.")).build();
			}
			return Response.status(Response.Status.OK).entity(new KorisnikResponse(k)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error occurred.")).build();
		}
	}
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("volonteri")
	public Response indexVolonteri(@Context HttpServletRequest request){
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isAdmin(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		try {
			ArrayList<Korisnik> vols = gc.getVolonteri();
			ArrayList<KorisnikResponse> retVal = new ArrayList<KorisnikResponse>();
			for (Korisnik v : vols) {
				retVal.add(new KorisnikResponse(v));
			}
			return Response.status(Response.Status.OK).entity(retVal).build();
		}catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error.")).build();
		}
	}
	
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("korisnici")
	public Response create(@Context HttpServletRequest request, KorisnikCreateRequest kcr) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		 Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
		 if (loggedInUser != null) {
			 return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("You must logout in order to register.")).build();
		 }
		
		try {
			Korisnik found = gc.findKorisnik(kcr.getKorisnickoIme());
			if (found != null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Username is already taken.")).build();
			}
			
			Korisnik k = new Korisnik();
			k.setKorisnickoIme(kcr.getKorisnickoIme());
			k.setEmail(kcr.getEmail());
			k.setIme(kcr.getIme());
			k.setPassword(kcr.getPassword());
			k.setPathSlike(kcr.getPathSlike());
			k.setPrezime(kcr.getPrezime());
			k.setTelefon(kcr.getTelefon());
			k.setVrsta(Vrsta.VOLONTER);
			k.setStanjeVolontera(StanjeVolontera.NORMALNO);
			
			if (kcr.getTeritorijaId() >= 1) {
				Teritorija t = gc.findTeritorija(kcr.getTeritorijaId());
				if (t == null) {
					return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Specified teriorija does not exist.")).build();
				}
				k.setTeritorija(t);
			}
			
			gc.addKorisnik(k);
			session.setAttribute("user", k);
			return Response.status(Response.Status.OK).entity(new KorisnikResponse(k)).build();
		} catch(Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error.")).build();
		}	
	}
	
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("korisnici/{kime}")
	public Response update(@PathParam("kime") String kime, @Context HttpServletRequest request, KorisnikCreateRequest kcr) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isLog(request)) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("You must be registred to edit your data.")).build();
		}

		try {
			Korisnik k = (Korisnik) session.getAttribute("user");
			if (!kime.equals(k.getKorisnickoIme())) {
				return Response.status(Response.Status.FORBIDDEN).entity(new ErrorModel("You cannot edit data of otehr user.")).build();
			}
			
			k.setEmail(kcr.getEmail());
			k.setIme(kcr.getIme());
			k.setPassword(kcr.getPassword());
			k.setPrezime(kcr.getPrezime());
			k.setTelefon(kcr.getTelefon());
			
			if (kcr.getTeritorijaId() >= 1) {
				Teritorija t = gc.findTeritorija(kcr.getTeritorijaId());
				if (t == null) {
					return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Specified teriorija does not exist.")).build();
				}
				k.setTeritorija(t);
			}
			
			gc.updateKorisnik(k, k.getKorisnickoIme());
			return Response.status(Response.Status.OK).entity(new KorisnikResponse(k)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error.")).build();
		}
	}
	
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("korisnici/{kime}/update-avatar")
	public Response updateImage(@PathParam("kime") String kime, @Context HttpServletRequest request, UpdateAvatarRequest uar) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isLog(request)) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("You must be registred to edit your data.")).build();
		}

		try {
			Korisnik k = (Korisnik) session.getAttribute("user");
			if (!kime.equals(k.getKorisnickoIme())) {
				return Response.status(Response.Status.FORBIDDEN).entity(new ErrorModel("You cannot edit data of otehr user.")).build();
			}
			
			k.setPathSlike(uar.getPathSlike());
			
			gc.updateKorisnik(k, k.getKorisnickoIme());
			return Response.status(Response.Status.OK).entity(new KorisnikResponse(k)).build();
		}catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error.")).build();
		}
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("korisnici/{kime}/block")
	public Response block(@PathParam("kime") String kime, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isAdmin(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		try {
			Korisnik k = gc.findKorisnik(kime);
			if (k == null ||  k.getVrsta().equals(Vrsta.ADMIN)) {
				return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("You cannot block unexisting user or admin.")).build();
			}
			k.setStanjeVolontera(StanjeVolontera.BLOKIRANO);
			return Response.status(Response.Status.OK).build();
		} catch(Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error.")).build();
		}
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("korisnici/{kime}/unblock")
	public Response unblock(@PathParam("kime") String kime, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isAdmin(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		try {
			Korisnik k = gc.findKorisnik(kime);
			if (k == null ||  k.getVrsta().equals(Vrsta.ADMIN)) {
				return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("You cannot unblock unexisting user or admin.")).build();
			}
			k.setStanjeVolontera(StanjeVolontera.NORMALNO);
			return Response.status(Response.Status.OK).build();
		} catch(Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error.")).build();
		}
	}
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("korisnici/{kime}")
	public Response delete(@PathParam("kime") String kime, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isLog(request)) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("You must be registred to edit your data.")).build();
		}
		
		try {
			Korisnik user = (Korisnik) session.getAttribute("user");
			if (!kime.equals(user.getKorisnickoIme())) {
				return Response.status(Response.Status.FORBIDDEN).entity(new ErrorModel("You cannotdeactivate account of otehr user.")).build();
			}
			gc.removeKorisnik(user);
			return Response.status(Response.Status.OK).build();
		}catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error.")).build();
		}
	}
}

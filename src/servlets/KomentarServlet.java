package servlets;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.GrandContainer;
import beans.Komentar;
import beans.Korisnik;
import beans.VanrednaSituacija;
import beans.enums.StanjeVSituacije;
import helpers.AuthorizationService;
import helpers.ErrorModel;
import requests.KomentarCreateRequest;
import responses.KomentarResponse;


@Path("/api")
public class KomentarServlet {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("komentari/{komentarId}")
	public Response findById(@PathParam("komentarId") long id, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isLog(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		Komentar found = gc.findKomentar(id);
		
		if (found == null)
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Cannot find specified komentar.")).build();
		
		try {
			KomentarResponse kr = new KomentarResponse(found);
			return Response.status(Response.Status.OK).entity(kr).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error occurred.")).build();
		}
	}
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("vsituacije/{vsId}/komentari")
	public Response index(@PathParam("vsId") long id, @Context HttpServletRequest request){
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isLog(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		ArrayList<KomentarResponse> vsr = new ArrayList<KomentarResponse>();
		VanrednaSituacija vs = gc.findVSituacija(id);
		
		if (vs == null)
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Cannot find specified vanredna situacija.")).build();

		try {
			for (Komentar k : vs.getKomentari()) {
				vsr.add(new KomentarResponse(k));
			}
			Collections.reverse(vsr);
			return Response.status(Response.Status.OK).entity(vsr).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error occurred.")).build();
		}
	}
	
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("komentari")
	public Response create(@Context HttpServletRequest request, KomentarCreateRequest kcr) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isLog(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		try {
			Korisnik k = gc.findKorisnik(kcr.getKorisnikKime());
			VanrednaSituacija vs = gc.findVSituacija(kcr.getVsId());
			Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
			
			if (k == null || vs == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Specified komentar or vanredna situacija does not exist.")).build();
			}
			
			if (!loggedInUser.getKorisnickoIme().equals(k.getKorisnickoIme())) {
				return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You cannot post komentar as oper user.")).build();
			}
			
			if (vs.getStanje().equals(StanjeVSituacije.ARHIVIRANO)) {
				return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("You cannot comment on archived vanredna situacija,")).build();
			}
			
			Komentar newKomentar = new Komentar();
			newKomentar.setId(gc.getNextKomentarID());
			newKomentar.setKorisnik(k);
			newKomentar.setTekst(kcr.getTekst());
			newKomentar.setVs(vs);
			vs.getKomentari().add(newKomentar);
			
			gc.addKomentar(newKomentar);
			return Response.status(Response.Status.OK).entity(new KomentarResponse(newKomentar)).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}

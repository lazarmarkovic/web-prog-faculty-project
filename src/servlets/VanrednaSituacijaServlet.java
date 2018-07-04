package servlets;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.GrandContainer;
import beans.Korisnik;
import beans.Teritorija;
import beans.VanrednaSituacija;
import beans.enums.StanjeVSituacije;
import helpers.AuthorizationService;
import helpers.ErrorModel;
import requests.SetVolonterOnVSRequest;
import requests.VSCreateRequest;
import responses.VSResponse;

@Path("api")
public class VanrednaSituacijaServlet {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("vsituacije/{vsId}")
	public Response findById(@PathParam("vsId") long id, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isLog(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		VanrednaSituacija found = gc.findVSituacija(id);
		if (found == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Cannot find specified komentar.")).build();
		}
		
		try {
			VSResponse vsr = new VSResponse(found);
			return Response.status(Response.Status.OK).entity(vsr).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error occurred.")).build();
		}
	}
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("vsituacije")
	public Response index(
			@QueryParam("poDatumu") boolean poDatumu, 
			@QueryParam("poHitnosti") boolean poHitnosti,
			@QueryParam("poTeritoriji") boolean poTeritoriji,
			@QueryParam("terId") long terId,
			@QueryParam("hitnost") int hitnost,
			@QueryParam("poNazTer") boolean poNazTer,
			@QueryParam("poNazOpst") boolean poNazOpst,
			@QueryParam("poOpisu") boolean poOpisu,
			@QueryParam("poVol") boolean poVol,
			@QueryParam("poPraz") boolean poPraz,
			@QueryParam("traziOvo") String traziOvo,
			@QueryParam("showMyVS") boolean showMyVS,
			@Context HttpServletRequest request){
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isLog(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		try {
			ArrayList<VanrednaSituacija> vss = new ArrayList<VanrednaSituacija>();
			gc.initSearch();
			gc.setupFilters(poDatumu, poHitnosti, poTeritoriji, showMyVS, terId, hitnost, ac.getUser(request));
			gc.setupSearch(poNazTer, poNazOpst, poOpisu, poVol, poPraz, traziOvo);
			vss = gc.getFilterSearchResults();
			
			ArrayList<VSResponse> vsrs = new ArrayList<VSResponse>();
			for (VanrednaSituacija vs : vss) {
				vsrs.add(new VSResponse(vs));
			}
			return Response.status(Response.Status.OK).entity(vsrs).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error occurred.")).build();
		}
	}
	
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("vsituacije")
	public Response create(@Context HttpServletRequest request, VSCreateRequest vscr) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isLog(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		try {
			VanrednaSituacija vs = new VanrednaSituacija();
			vs.setId(gc.getNextVSituacijaID());
			Teritorija t = gc.findTeritorija(vscr.getTeritorijaId());
			Korisnik v = null;
			if (vscr.getVolonterKime() != null)
				v = gc.findKorisnik(vscr.getVolonterKime());
			if (t == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Specified teritorija does not exist.")).build();
			}
			vs.setNazivMesta(vscr.getNazivMesta());
			vs.setNivoHitnosti(vscr.getNivoHitnosti());
			vs.setOpis(vscr.getOpis());
			vs.setOpstina(vscr.getOpstina());
			vs.setPathSlike(vscr.getPathSlike());
			vs.setStanje(StanjeVSituacije.AKTIVNO);
			vs.setTacnaLokacija(vscr.getTacnaLokacija());
			vs.setTeritorija(t);
			if (v != null) {
				vs.setVolonter(v);
				v.getVanredneSituacije().add(vs);
			}
				
			gc.addvSituacija(vs);
			return Response.status(Response.Status.OK).entity(new VSResponse(vs)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error occurred.")).build();
		}
	}
	
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("vsituacije/{vsId}/set-volonter")
	public Response setVolonter(@PathParam("vsId") long id, @Context HttpServletRequest request, SetVolonterOnVSRequest setv) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isAdmin(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		try {
			VanrednaSituacija vs = gc.findVSituacija(id);
			if (vs == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Specified vanredna situacija does not exist.")).build();
			}
			
			Korisnik v = gc.findKorisnik(setv.getVolonterKime());
			if (v == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Specified volonter does not exist.")).build();
			}
			Korisnik stariVolonter = vs.getVolonter();
			if (stariVolonter != null)
				stariVolonter.removeVanrednaSituacija(vs);
			vs.setVolonter(v);
			v.getVanredneSituacije().add(vs);
			
			gc.updateVSituacija(vs, id);
			return Response.status(Response.Status.OK).entity(new VSResponse(vs)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Unknown error occured.")).build();
		}
	}
	
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("vsituacije/{vsId}/archive")
	public Response archive(@PathParam("vsId") long id, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		GrandContainer gc = (GrandContainer) context.getAttribute("DATA");
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isAdmin(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		try {
			VanrednaSituacija vs = gc.findVSituacija(id);
			if (vs == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("Specified vanredna situacija does not exist.")).build();
			}
			vs.setStanje(StanjeVSituacije.ARHIVIRANO);
			gc.updateVSituacija(vs, id);
			return Response.status(Response.Status.OK).entity(new VSResponse(vs)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}

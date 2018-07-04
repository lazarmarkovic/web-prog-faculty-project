package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import helpers.ErrorModel;
import helpers.Storage;

@Path("")
public class StaticFilesDownloadServlet {
	
	private boolean illegal(String s) {
		s = s.toLowerCase();
		if (s.contains("..") || s.contains("storage") || s.contains("meta-inf") || s.contains("web-inf")) {
			return true;
		}
		return false;
	}
	
	@GET
	@Produces({"text/html"})
	@Path("")
	public Response downloadHTMLFileIndex(HttpServletResponse response) {
		URI path;
		try {
			path = new URI("http://localhost/WebProjekat/index.html");
			return Response.seeOther(path).build();
		} catch (URISyntaxException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Produces({"text/html"})
	@Path("/{path:.*.html}")
	public Response downloadHTMLFile(@PathParam("path") String path) {
		if (this.illegal(path)) {
			return Response.status(Response.Status.FORBIDDEN).entity(new ErrorModel("You are denied access to this resource.")).build();
		}
		
		String folderPath = Storage.getInstance().getSTATIC_PATH();
		String filePath = folderPath + System.getProperty("file.separator") + path;
		
	    try {
			File file = new File(filePath);
			return Response.status(Response.Status.OK).entity(new FileInputStream(file)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("File not found.")).build();
		}
	}
	
	@GET
	@Produces({"text/css"})
	@Path("/{path:.*.css}")
	public Response downloadCSSFile(@PathParam("path") String path) {
		if (this.illegal(path)) {
			return Response.status(Response.Status.FORBIDDEN).entity(new ErrorModel("You are denied access to this resource.")).build();
		}
		
		String folderPath = Storage.getInstance().getSTATIC_PATH();
		String filePath = folderPath + System.getProperty("file.separator") + path;
		
	    try {
			File file = new File(filePath);
			return Response.status(Response.Status.OK).entity(new FileInputStream(file)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("File not found.")).build();
		}
	}
	
	@GET
	@Produces({"application/javascript"})
	@Path("/{path:.*.js}")
	public Response downloadJSFile(@PathParam("path") String path) {
		if (this.illegal(path)) {
			return Response.status(Response.Status.FORBIDDEN).entity(new ErrorModel("You are denied access to this resource.")).build();
		}
		
		String folderPath = Storage.getInstance().getSTATIC_PATH();
		String filePath = folderPath + System.getProperty("file.separator") + path;
		
	    try {
			File file = new File(filePath);
			return Response.status(Response.Status.OK).entity(new FileInputStream(file)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("File not found.")).build();
		}
	}
	
	@GET
	@Produces({"image/jpg"})
	@Path("/{path:.*.jpg}")
	public Response downloadJPGFile(@PathParam("path") String path) {
		if (this.illegal(path)) {
			return Response.status(Response.Status.FORBIDDEN).entity(new ErrorModel("You are denied access to this resource.")).build();
		}
		
		String folderPath = Storage.getInstance().getSTATIC_PATH();
		String filePath = folderPath + System.getProperty("file.separator") + path;
		
	    try {
			File file = new File(filePath);
			return Response.status(Response.Status.OK).entity(new FileInputStream(file)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("File not found.")).build();
		}
	}
	
	@GET
	@Produces({"image/svg"})
	@Path("/{path:.*.svg}")
	public Response downloadSVGFile(@PathParam("path") String path) {
		if (this.illegal(path)) {
			return Response.status(Response.Status.FORBIDDEN).entity(new ErrorModel("You are denied access to this resource.")).build();
		}
		
		String folderPath = Storage.getInstance().getSTATIC_PATH();
		String filePath = folderPath + System.getProperty("file.separator") + path;
		
	    try {
			File file = new File(filePath);
			return Response.status(Response.Status.OK).entity(new FileInputStream(file)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("File not found.")).build();
		}
	}
	
	@GET
	@Produces({"image/icon"})
	@Path("/{path:.*.ico}")
	public Response downloadICOFile(@PathParam("path") String path) {
		if (this.illegal(path)) {
			return Response.status(Response.Status.FORBIDDEN).entity(new ErrorModel("You are denied access to this resource.")).build();
		}
		
		String folderPath = Storage.getInstance().getSTATIC_PATH();
		String filePath = folderPath + System.getProperty("file.separator") + path;
		
	    try {
			File file = new File(filePath);
			return Response.status(Response.Status.OK).entity(new FileInputStream(file)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorModel("File not found.")).build();
		}
	}
}

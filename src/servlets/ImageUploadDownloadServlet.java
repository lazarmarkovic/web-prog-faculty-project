package servlets;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

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
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import helpers.AuthorizationService;
import helpers.ErrorModel;
import helpers.Storage;
import responses.ImageUploadResponse;

@Path("/api")
public class ImageUploadDownloadServlet {
	
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadImageFile(
	        @FormDataParam("file") InputStream uploadedInputStream,
	        @FormDataParam("file") FormDataContentDisposition fileDetail,
	        @Context HttpServletRequest request) {
		
		/*HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isLog(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}*/
	
		try {
			String fileName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
			String folderPath = Storage.getInstance().getIMAGES_PATH();
			String imagePath = folderPath + System.getProperty("file.separator") + fileName;
			
			File fileSaveDir = new File(folderPath);
			if(!fileSaveDir.exists()){
			    fileSaveDir.mkdir();
			}
			
			File targetFile = new File(imagePath);
			java.nio.file.Files.copy(
						uploadedInputStream, 
						targetFile.toPath(), 
						StandardCopyOption.REPLACE_EXISTING);
			
			
			return Response.status(Response.Status.OK).entity(new ImageUploadResponse(fileName)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.OK).entity(new ErrorModel("Unknown error occurred.")).build();
		}
	}
	
	
	@GET
	@Produces({"image/jpg"})
	@Path("download/{filename}")
	public Response downloadFile(@PathParam("filename") String fileName, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		
		AuthorizationService ac = (AuthorizationService) context.getAttribute("AUTH_SERVICE");
		if (!ac.isLog(request)) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorModel("You do not have access rights to this operation.")).build();
		}
		
		String folderPath = Storage.getInstance().getIMAGES_PATH();
		String imagePath = folderPath + System.getProperty("file.separator") + fileName;
		try {
			File file = new File(imagePath);
			 return Response.ok(file)
				        .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
				        .build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(new ErrorModel("No such file.")).build();
		}
	    
	   
	}
}

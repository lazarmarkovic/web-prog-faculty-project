package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import beans.GrandContainer;
import helpers.AuthorizationService;
import helpers.ObjectSerializer;
import helpers.Storage;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		ServletContext ctx = servletContextEvent.getServletContext();
		
		Storage.getInstance().setDATAC_FILE(ctx.getRealPath("") + "Storage" + 
					System.getProperty("file.separator") + "Data" + System.getProperty("file.separator") + "DataContainer.dat");
		
		Storage.getInstance().setADMIN_FILE(ctx.getRealPath("") + "Storage" + 
				System.getProperty("file.separator") + "Data" + System.getProperty("file.separator") + "AdminsData.txt");
		
		Storage.getInstance().setIMAGES_PATH(ctx.getRealPath("") + "Storage" + 
				System.getProperty("file.separator") + "Images" + System.getProperty("file.separator"));
		
		Storage.getInstance().setSTATIC_PATH(ctx.getRealPath(""));
		
    	System.out.println(Storage.getInstance().getDATAC_FILE());
    	System.out.println(Storage.getInstance().getADMIN_FILE());
    	System.out.println(Storage.getInstance().getIMAGES_PATH());
    	System.out.println(Storage.getInstance().getSTATIC_PATH());
    	
    	GrandContainer gc = ObjectSerializer.getInstance().initObjectFileOrRead();
    	ctx.setAttribute("DATA", gc);
    	
    	AuthorizationService as = AuthorizationService.getInstance();
    	ctx.setAttribute("AUTH_SERVICE", as);
	}

}

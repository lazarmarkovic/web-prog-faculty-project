package servlets;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/")
public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // register root resource
        classes.add(AuthServlet.class);
        classes.add(KomentarServlet.class);
        classes.add(KorisnikServlet.class);
        classes.add(TeritorijaServlet.class);
        classes.add(VanrednaSituacijaServlet.class);
        classes.add(ImageUploadDownloadServlet.class);
        classes.add(StaticFilesDownloadServlet.class);
        
        return classes;
    }
}


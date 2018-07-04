package helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Korisnik;
import beans.enums.StanjeVolontera;
import beans.enums.Vrsta;

public class AuthorizationService {
	private static AuthorizationService instance = null;
	
	protected AuthorizationService() {}
	
	public static AuthorizationService getInstance() {
		if(instance == null) {
			instance = new AuthorizationService();
		}
		return instance;
	}
	
	public Korisnik getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
        Korisnik user = (Korisnik) session.getAttribute("user");
        return user;
	}
	
	public boolean isAdmin(HttpServletRequest request)
	{
		
        Korisnik user = this.getUser(request);
        if (user == null)
        	return false;
        
        if (user.getVrsta().equals(Vrsta.ADMIN)){
        	return true;
        }
        return false;
	}
	
	public boolean isLog(HttpServletRequest request)
	{
		Korisnik user = this.getUser(request);
        
        if (user == null)
        	return false;
        
        if (user.getVrsta().equals(Vrsta.ADMIN) || user.getVrsta().equals(Vrsta.VOLONTER) && user.getStanjeVolontera().equals(StanjeVolontera.NORMALNO)) {
        	return true;
        }
        return false;
	}
	
	
	
}

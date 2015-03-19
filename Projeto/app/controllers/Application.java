package controllers;

import java.util.*;
import models.Usuario;
import models.Tema;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.api.mvc.Session;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	private static GenericDAO dao = new GenericDAOImpl();
	
	@Transactional
    public static Result index() {
		//session().clear();
		if (session().get("email") == null) {
        	return redirect(routes.Login.show());
        }
		if(dao.findByAttributeName("Usuario",
				"email", session().get("email")).size() == 0) {
					session().clear();
					return redirect(routes.Login.show());
		}
        Usuario usuario = (Usuario) dao.findByAttributeName("Usuario",
				"email", session().get("email")).get(0);
        List<Tema> temas = dao.findAllByClassName("Tema");
        return ok(index.render(usuario, temas));
        // return redirect(routes.Login.show());
    }
    
    
}

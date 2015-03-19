package controllers;

import java.util.*;

import models.Disciplina;
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
        
        List<Disciplina> disciplina = dao.findByAttributeName("Disciplina", "nome","SI1");
        return ok(index.render(usuario, disciplina.get(0)));
        // return redirect(routes.Login.show());
    }
    
	@Transactional
    public static Result getPaginaTema(Long id) {
		
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
		
		Tema tema = (Tema)dao.findByEntityId(Tema.class, id);
		if(tema == null) return badRequest();
		return ok(paginaTema.render(usuario, tema));
	}
}

package controllers;

import java.util.LinkedList;
import java.util.List;

import models.Dica;
import models.DicaMaterial;
import models.DicaSimples;
import models.Disciplina;
import models.Tema;
import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.paginaTema;

public class Application extends Controller {

	private static GenericDAO dao = new GenericDAOImpl();
	private static Form<DicaMaterial> addDicaForm = Form.form(DicaMaterial.class).bindFromRequest();
	
	@Transactional
    public static Result index() {
		//session().clear();
		if (session().get("email") == null) {
        	return redirect(routes.Login.show());
        }
		if(naoExisteUsuarioLogado()) {
					session().clear();
					return redirect(routes.Login.show());
		}
        Usuario usuario = getUsuarioLogado();
        
        List<Disciplina> disciplina = dao.findByAttributeName("Disciplina", "nome","SI1");
        return ok(index.render(usuario, disciplina.get(0)));
        // return redirect(routes.Login.show());
    }
    
	@Transactional
    public static Result getPaginaTema(Long id) {
		
		if (session().get("email") == null) {
        	return redirect(routes.Login.show());
        }
		if(naoExisteUsuarioLogado()) {
					session().clear();
					return redirect(routes.Login.show());
		}
        Usuario usuario = getUsuarioLogado();
		
		Tema tema = (Tema)dao.findByEntityId(Tema.class, id);
		if(tema == null) return badRequest();
		return ok(paginaTema.render(usuario, tema, addDicaForm));
	}
	
	@Transactional
	public static Result votar(Long id, Integer v) {
		if(v > 2 || v < -2) return badRequest();
		if (session().get("email") == null) {
        	return redirect(routes.Login.show());
        }
		if(naoExisteUsuarioLogado()) {
					session().clear();
					return redirect(routes.Login.show());
		}
        Usuario usuario = getUsuarioLogado();
        Tema tema = (Tema)dao.findByEntityId(Tema.class, id);
		if(tema == null) return badRequest();
		tema.votar(usuario, v);
		dao.merge(tema);
		dao.flush();
		return ok(paginaTema.render(usuario, tema, addDicaForm));
	
	}
	
	@Transactional
	public static Result adicionarDica(Long id) {
		if (session().get("email") == null) {
        	return redirect(routes.Login.show());
        }
		if(naoExisteUsuarioLogado()) {
					session().clear();
					return redirect(routes.Login.show());
		}
		addDicaForm = Form.form(DicaMaterial.class).bindFromRequest();
		Tema tema = (Tema)dao.findByEntityId(Tema.class, id);
		DicaSimples dica = addDicaForm.get();
		dao.persist(dica);
		tema.addDica(dica);
		dao.merge(tema);
		dao.flush();
		return ok(paginaTema.render(getUsuarioLogado(), tema, addDicaForm));
	}
	
	@Transactional
	public static Result votarDica(Long idTema, Long idDica, Integer v) {
		if (session().get("email") == null) {
        	return redirect(routes.Login.show());
        }
		if(naoExisteUsuarioLogado()) {
					session().clear();
					return redirect(routes.Login.show());
		}
		Dica dica = (Dica)dao.findByEntityId(Dica.class, idDica);
		Tema tema = (Tema)dao.findByEntityId(Tema.class, idTema);
		dica.votar(v);
		dao.merge(dica);
		tema = (Tema)dao.findByEntityId(Tema.class, idTema);
		tema.sortDicas();
		dao.merge(tema);
		dao.flush();
		return ok(paginaTema.render(getUsuarioLogado(), tema, addDicaForm));
		
	}
		
	private static Usuario getUsuarioLogado() {
		return (Usuario) dao.findByAttributeName("Usuario",
				"email", session().get("email")).get(0);
	}

	private static boolean naoExisteUsuarioLogado() {
		return dao.findByAttributeName("Usuario",
				"email", session().get("email")).size() == 0;
	}
}

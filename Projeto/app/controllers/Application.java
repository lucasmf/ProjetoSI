package controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import models.Comentario;
import models.Dica;
import models.DicaAssunto;
import models.DicaConselho;
import models.DicaDisciplina;
import models.DicaMaterial;
import models.DicaSimples;
import models.Disciplina;
import models.Metadica;
import models.Tema;
import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.paginaTema;

public class Application extends Controller {

	private static GenericDAO dao = new GenericDAOImpl();
	private static Form<DicaMaterial> addDicaMaterialForm = Form.form(
			DicaMaterial.class).bindFromRequest();
	private static Form<DicaDisciplina> addDicaDisciplinaForm = Form.form(
			DicaDisciplina.class).bindFromRequest();
	private static Form<DicaAssunto> addDicaAssuntoForm = Form.form(
			DicaAssunto.class).bindFromRequest();
	private static Form<DicaConselho> addDicaConselhoForm = Form.form(
			DicaConselho.class).bindFromRequest();
	private static Form<Comentario> formComentario = Form.form(
			Comentario.class).bindFromRequest();
	
	@Transactional
	public static Result index() {
		if (session().get("email") == null) {
			return redirect(routes.Login.show());
		}
		if (naoExisteUsuarioLogado()) {
			session().clear();
			return redirect(routes.Login.show());
		}
		Usuario usuario = getUsuarioLogado();

		List<Disciplina> disciplina = dao.findByAttributeName("Disciplina",
				"nome", "SI1");
		return ok(index.render(usuario, disciplina.get(0)));
	}

	@Transactional
	public static Result returnPaginaTema(Usuario usuario, Tema tema) {
		return ok(paginaTema.render(usuario, tema, addDicaMaterialForm,
				addDicaDisciplinaForm, addDicaAssuntoForm, addDicaConselhoForm, formComentario));
	}

	@Transactional
	public static Result getPaginaTema(Long id) {

		if (session().get("email") == null) {
			return redirect(routes.Login.show());
		}
		if (naoExisteUsuarioLogado()) {
			session().clear();
			return redirect(routes.Login.show());
		}
		Usuario usuario = getUsuarioLogado();

		Tema tema = (Tema) dao.findByEntityId(Tema.class, id);
		if (tema == null)
			return badRequest();
		tema.sortDicas();
		dao.merge(tema);
		dao.flush();
		return returnPaginaTema(usuario, tema);
	}

	@Transactional
	public static Result votar(Long id, Integer v) {
		if (v > 2 || v < -2)
			return badRequest();
		if (session().get("email") == null) {
			return redirect(routes.Login.show());
		}
		if (naoExisteUsuarioLogado()) {
			session().clear();
			return redirect(routes.Login.show());
		}
		Usuario usuario = getUsuarioLogado();
		Tema tema = (Tema) dao.findByEntityId(Tema.class, id);
		if (tema == null)
			return badRequest();
		tema.votar(usuario, v);
		tema.sortDicas();
		dao.merge(tema);
		dao.flush();
		return redirect(routes.Application.getPaginaTema(id));

	}

	@Transactional
	public static Result adicionarDicaMaterial(Long id) {
		if (session().get("email") == null) {
			return redirect(routes.Login.show());
		}
		if (naoExisteUsuarioLogado()) {
			session().clear();
			return redirect(routes.Login.show());
		}
		addDicaMaterialForm = Form.form(DicaMaterial.class).bindFromRequest();
		Tema tema = (Tema) dao.findByEntityId(Tema.class, id);
		DicaMaterial dica = (DicaMaterial) addDicaMaterialForm.get();
		addDicaSimplesNoBD(tema, dica);
		return redirect(routes.Application.getPaginaTema(id));
	}
	

	@Transactional
	public static Result adicionarDicaDisciplina(Long id) {
		if (session().get("email") == null) {
			return redirect(routes.Login.show());
		}
		if (naoExisteUsuarioLogado()) {
			session().clear();
			return redirect(routes.Login.show());
		}
		addDicaDisciplinaForm = Form.form(DicaDisciplina.class)
				.bindFromRequest();
		Tema tema = (Tema) dao.findByEntityId(Tema.class, id);
		DicaDisciplina dica = (DicaDisciplina) addDicaDisciplinaForm.get();
		addDicaSimplesNoBD(tema, dica);
		return redirect(routes.Application.getPaginaTema(id));
	}

	
	@Transactional
	public static Result adicionarDicaAssunto(Long id) {
		if (session().get("email") == null) {
			return redirect(routes.Login.show());
		}
		if (naoExisteUsuarioLogado()) {
			session().clear();
			return redirect(routes.Login.show());
		}
		addDicaAssuntoForm = Form.form(DicaAssunto.class).bindFromRequest();
		Tema tema = (Tema) dao.findByEntityId(Tema.class, id);
		DicaAssunto dica = (DicaAssunto) addDicaAssuntoForm.get();
		addDicaSimplesNoBD(tema, dica);
		return redirect(routes.Application.getPaginaTema(id));
	}

	@Transactional
	public static Result adicionarDicaConselho(Long id) {
		if (session().get("email") == null) {
			return redirect(routes.Login.show());
		}
		if (naoExisteUsuarioLogado()) {
			session().clear();
			return redirect(routes.Login.show());
		}
		addDicaConselhoForm = Form.form(DicaConselho.class).bindFromRequest();
		Tema tema = (Tema) dao.findByEntityId(Tema.class, id);
		DicaConselho dica = (DicaConselho) addDicaConselhoForm.get();
		addDicaSimplesNoBD(tema, dica);
		return redirect(routes.Application.getPaginaTema(id));
	}
	
	private static void addDicaSimplesNoBD(Tema tema, DicaSimples dica) {
		dao.persist(dica);
		tema.addDica(dica);
		tema.sortDicas();
		dao.merge(tema);
		dao.flush();
	}

	@Transactional
	public static Result votarDica(Long idTema, Long idDica, Integer v) {
		if (session().get("email") == null) {
			return redirect(routes.Login.show());
		}
		if (naoExisteUsuarioLogado()) {
			session().clear();
			return redirect(routes.Login.show());
		}
		Dica dica = (Dica) dao.findByEntityId(Dica.class, idDica);
		Tema tema = (Tema) dao.findByEntityId(Tema.class, idTema);
		Usuario usuario = getUsuarioLogado();
		dica.votar(usuario.getId(), v);
		dao.merge(dica);
		tema = (Tema) dao.findByEntityId(Tema.class, idTema);
		tema.sortDicas();
		dao.merge(tema);
		dao.flush();
		return redirect(routes.Application.getPaginaTema(idTema));
	}

	@Transactional
	public static Result adicionarComentario(Long idTema, Long idDica) {
		if (session().get("email") == null) {
			return redirect(routes.Login.show());
		}
		if (naoExisteUsuarioLogado()) {
			session().clear();
			return redirect(routes.Login.show());
		}
		
		Usuario usuario = getUsuarioLogado();
		Dica dica = (Dica) dao.findByEntityId(Dica.class, idDica);
		Tema tema = (Tema) dao.findByEntityId(Tema.class, idTema);
		formComentario = Form.form(Comentario.class).bindFromRequest();
		Comentario comentario = formComentario.get();
		dica.addComentario(comentario.getComentario());
		tema.sortDicas();
		dao.merge(dica);
		dao.merge(tema);
		dao.flush();
		return redirect(routes.Application.getPaginaTema(idTema));
	}
	
	 @Transactional
	    public static Result addMetadica(Long disciplinaId) {
	        DynamicForm filledForm = Form.form().bindFromRequest();
	        String comentario = filledForm.get("comentario");
	        Map<String, String> form = filledForm.data();
	        List<Dica> dicas = new ArrayList<Dica>();

	        for (int i = 0; i<form.size()-1; i++){
	            dicas.add(dao.findByEntityId(Dica.class, Long.parseLong(form.get("dicas["+i+"]"))));
	        }
	        Disciplina disciplina = (Disciplina)dao.findByEntityId(Disciplina.class, disciplinaId);
	        Metadica metadica = new Metadica(comentario, dicas);
            disciplina.addMetadica(metadica);
            dao.persist(metadica);
            dao.merge(disciplina);
            //dao.flush();
            return redirect(routes.Application.index());
            
	    }

	
	
	private static Usuario getUsuarioLogado() {
		return (Usuario) dao.findByAttributeName("Usuario", "email",
				session().get("email")).get(0);
	}

	private static boolean naoExisteUsuarioLogado() {
		return dao.findByAttributeName("Usuario", "email",
				session().get("email")).size() == 0;
	}
}

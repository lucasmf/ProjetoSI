package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Comentario;
import models.Dica;
import models.Disciplina;
import models.Metadica;
import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	private static GenericDAO dao = new GenericDAOImpl();
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

		List<Disciplina> disciplinas = dao.findByAttributeName("Disciplina",
				"nome", "SI1");
		Disciplina disciplina = disciplinas.get(0);
		disciplina.sortMetadicas();
		dao.merge(disciplina);
		dao.flush();
		return ok(index.render(usuario, disciplina, formComentario));
	}

	@Transactional
	public static Result addMetadica(Long disciplinaId) {
		DynamicForm filledForm = Form.form().bindFromRequest();
		String comentario = filledForm.get("comentario");
		Map<String, String> form = filledForm.data();
		List<Dica> dicas = new ArrayList<Dica>();

		for (int i = 0; i < form.size() - 1; i++) {
			dicas.add(dao.findByEntityId(Dica.class,
					Long.parseLong(form.get("dicas[" + i + "]"))));
		}
		Disciplina disciplina = (Disciplina) dao.findByEntityId(
				Disciplina.class, disciplinaId);
		Metadica metadica = new Metadica(comentario, dicas);
		disciplina.addMetadica(metadica);
		dao.persist(metadica);
		dao.merge(disciplina);
		// dao.flush();
		return redirect(routes.Application.index());

	}
	
	@Transactional
	public static Result votarMetadica(Long idDisciplina, Long idDica, int v) {
		if (session().get("email") == null) {
			return redirect(routes.Login.show());
		}
		if (naoExisteUsuarioLogado()) {
			session().clear();
			return redirect(routes.Login.show());
		}
		Dica dica = (Dica) dao.findByEntityId(Dica.class, idDica);
		Disciplina disciplina = (Disciplina) dao.findByEntityId(Disciplina.class, idDisciplina);
		Usuario usuario = getUsuarioLogado();
		dica.votar(usuario.getId(), v);
		dao.merge(dica);
		disciplina = (Disciplina) dao.findByEntityId(Disciplina.class, idDisciplina);
		disciplina.sortMetadicas();
		dao.merge(disciplina);
		return redirect(routes.Application.index());	
	}
	
	@Transactional
	public static Result adicionarComentario(Long idDisciplina, Long idDica) {
		if (session().get("email") == null) {
			return redirect(routes.Login.show());
		}
		if (naoExisteUsuarioLogado()) {
			session().clear();
			return redirect(routes.Login.show());
		}
		Dica dica = (Dica) dao.findByEntityId(Dica.class, idDica);
		Disciplina disciplina = (Disciplina) dao.findByEntityId(Disciplina.class, idDisciplina);
		formComentario = Form.form(Comentario.class).bindFromRequest();
		Comentario comentario = formComentario.get();
		dica.addComentario(comentario.getComentario());
		dao.merge(dica);
		dao.merge(disciplina);
		dao.flush();
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

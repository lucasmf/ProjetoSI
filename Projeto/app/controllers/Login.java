package controllers;

import java.util.List;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

public class Login extends Controller {

	private static GenericDAO dao = new GenericDAOImpl();
	private static Form<Usuario> loginForm = Form.form(Usuario.class).bindFromRequest();
	
	@Transactional
	public static Result show() {
		if (session().get("email") != null) {
			return redirect(routes.Application.index());
		}
		return ok(login.render(loginForm));
	}

	@Transactional
	public static Result authenticate() {
		loginForm = Form.form(Usuario.class).bindFromRequest();
		Usuario u = loginForm.get();

		String email = u.getEmail();
		String senha = u.getPassword();
		if (loginForm.hasErrors() || !validate(email, senha)) {
			flash("fail", "Email ou Senha Inválidos");
			return badRequest(login.render(loginForm));
		} else {
			Usuario user = (Usuario) dao.findByAttributeName("Usuario",
					"email", u.getEmail()).get(0);
			session().clear();
			session("email", user.getEmail());
			return redirect(routes.Application.index());
		}
	}
	
	public static Result signUp() {
		System.out.println("oi");
		Form<Usuario> signUpForm = Form.form(Usuario.class).bindFromRequest();
		Usuario u = signUpForm.get();
		String email = u.getEmail();
		if (signUpForm.hasErrors()) {
			flash("fail", "Nome, email ou Senha Inválidos");
			return badRequest(login.render(signUpForm));
		} else if(validate(email)){
			flash("fail", "Email já existente");
			return badRequest(login.render(signUpForm));
		} else {
			dao.persist(u);
			dao.flush();
			return redirect(routes.Login.show());
		}
	}
	
	@Transactional
	private static boolean validate(String email) {
		List<Usuario> usuarios = dao.findByAttributeName("Usuario", "email", email);
		if(usuarios.size() == 0) {
			return false;
		}
		return true;
	}

	@Transactional
	public static boolean validate(String email, String senha) {
		List<Usuario> usuarios = dao.findAllByClassName("Usuario");
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(email)
					&& usuario.getPassword().equals(senha)) {
				return true;
			}
		}
		return false;
	}
	
	@Transactional
	 public static Result logout() {
	       session().clear();
	       return redirect(routes.Application.index());
	    }

}

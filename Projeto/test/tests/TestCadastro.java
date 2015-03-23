package tests;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeRequest;

import java.util.List;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Before;
import org.junit.Test;

import play.db.jpa.Transactional;
import base.AbstractTest;

import com.google.common.collect.ImmutableMap;

public class TestCadastro extends AbstractTest {
	private static GenericDAO dao = new GenericDAOImpl();
	
	@Before
	@Transactional
	public void setUp2() {
		callAction(controllers.routes.ref.Login.logout(), fakeRequest());
		Usuario usuario = new Usuario("casal20", "casal20@gmail.com", "casal20");
		List<Usuario> usuarios = dao.findByAttributeName("Usuario", "email",
				"casal20@gmail.com");
		if (usuarios.size() == 0) {
			dao.persist(usuario);
		}
	}
	
	@Test
	public void deveCadastrarUsuario () {
		callAction(
				controllers.routes.ref.Login.signUp(),
				fakeRequest().withFormUrlEncodedBody(
						ImmutableMap.of("nome", "nome", "email",
								"casal21@gmail.com", "password", "casal20")));
		List<Usuario> usuarios = dao.findByAttributeName("Usuario", "email",
				"casal21@gmail.com");
		assertEquals(usuarios.size(), 1);
	}
	
	@Test
	public void naoDeveCadastrarUsuario2Vezes () {
		callAction(
				controllers.routes.ref.Login.signUp(),
				fakeRequest().withFormUrlEncodedBody(
						ImmutableMap.of("nome", "nome", "email",
								"casal21@gmail.com", "password", "casal20")));
		callAction(
				controllers.routes.ref.Login.signUp(),
				fakeRequest().withFormUrlEncodedBody(
						ImmutableMap.of("nome", "nome", "email",
								"casal21@gmail.com", "password", "casal20")));
		List<Usuario> usuarios = dao.findByAttributeName("Usuario", "email",
				"casal21@gmail.com");
		assertEquals(usuarios.size(), 1);
	}
}

package tests;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeRequest;

import java.util.List;

import models.DicaAssunto;
import models.DicaSimples;
import models.Tema;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Before;
import org.junit.Test;

import play.api.mvc.*;
import play.mvc.Result;
import base.AbstractTest;
import play.mvc.Controller;
import play.data.Form;
/**
 * 
 * Simple (JUnit) tests that can call all parts of a play app. If you are
 * interested in mocking a whole application, see the wiki for more details.
 * 
 */
public class ApplicationTest extends AbstractTest {
	private static GenericDAO dao = new GenericDAOImpl();
	
	
	@Test
	public void simpleCheck() {
		int a = 1 + 1;
		assertThat(a).isEqualTo(2);
	}

/*	@Test
	public void testVotarDica() {
		Tema tema = new Tema("OO");
		DicaSimples dica = new DicaAssunto("Assunto");
		dao.persist(dica);
		DicaSimples dica2 = new DicaAssunto("Assunto2");
		dao.persist(dica2);
		tema.addDica(dica);
		tema.addDica(dica2);
		dao.persist(tema);
		dao.flush();
		
		Result result = callAction(
				controllers.routes.ref.Application.votarDica(tema.getId(),
						dica.getId(), 1), fakeRequest());
		result = callAction(controllers.routes.ref.Application.votarDica(
				tema.getId(), dica2.getId(), 1), fakeRequest());
		result = callAction(
				controllers.routes.ref.Application.votarDica(tema.getId(),
						dica.getId(), 0), fakeRequest());

		tema = dao.findByEntityId(Tema.class, tema.getId());
		List<DicaSimples> dicas = tema.getDicas();
		assertEquals(dica2.getId(), dicas.get(0).getId());
		assertEquals(dica.getId(), dicas.get(1).getId());
	}*/
	

	/*
	 * @Test public void renderTemplate() { Content html =
	 * views.html.index.render();
	 * assertThat(contentType(html)).isEqualTo("text/html");
	 * assertThat(contentAsString(html)).contains("SI1"); }
	 */

	/*
	 * @Test public void testApplicationIndex() { Result result =
	 * callAction(controllers.routes.ref.Application.index(), fakeRequest());
	 * assertThat(status(result)).isEqualTo(Http.Status.OK); }
	 */
}

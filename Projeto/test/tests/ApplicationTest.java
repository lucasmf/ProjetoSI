package tests;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.status;

import java.util.List;

import models.DicaAssunto;
import models.DicaSimples;
import models.Tema;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Before;
import org.junit.Test;

import controllers.ref.ReverseApplication;
import play.api.mvc.*;
import play.mvc.Http;
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
	public void testCallIndex() {
		Result result = callAction(controllers.routes.ref.Application.index(),
				fakeRequest());
		assertThat(status(result)).isEqualTo(Http.Status.OK);		
	} */

}

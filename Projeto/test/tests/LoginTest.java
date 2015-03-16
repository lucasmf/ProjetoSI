package tests;
import java.util.List;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;

import controllers.Login;
import base.AbstractTest;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.status;
import play.mvc.Http;
import play.mvc.Result;
import static play.test.Helpers.*;
import play.db.jpa.Transactional;
import play.libs.*;
//import com.avaje.ebean.Ebean;

public class LoginTest extends AbstractTest {
	private static GenericDAO dao = new GenericDAOImpl();
	
	@Before
	@Transactional
	public void setUp2() {
		callAction(controllers.routes.ref.Login.logout(),
				fakeRequest());
		Usuario usuario = new Usuario("casal20", "casal20@gmail.com", "casal20");
		List<Usuario> usuarios = dao.findByAttributeName("Usuario", "email", "casal20@gmail.com");
		if (usuarios.size() == 0) {
			dao.persist(usuario);
			//dao.flush();
		}

	}
	
	
	@Test
	public void testCallLogin() {
		Result result = callAction(controllers.routes.ref.Login.show(),
				fakeRequest());
		assertThat(status(result)).isEqualTo(Http.Status.OK);		
	} 
	
/*	@Test
	@Transactional
	public void testAuthenticateSuccess() {
	    Result result = callAction(
	        controllers.routes.ref.Login.authenticate(),
	        fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
	            "email", "casal20@gmail.com",
	            "password", "casal20"))
	    );
	    assertEquals(200, status(result));
	    assertEquals("casal20@gmail.com", session(result).get("email"));
	}
	*/
	@Test
	@Transactional
	public void testAuthenticateFailure() {
	    Result result = callAction(
	        controllers.routes.ref.Login.authenticate(),
	        fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
	            "email", "casal@gmail.com",
	            "password", "casal20"))
	    );
	    assertEquals(400, status(result));
	    assertEquals(null, session(result).get("email"));
	}
	
	@Test 
	public void testValidateSucess() {
		assertTrue(Login.validate("casal20@gmail.com", "casal20"));
	}	
	
	@Test 
	public void testValidateFailure() {
		assertFalse(Login.validate("casal@gmail.com", "casal20"));
	}	
}

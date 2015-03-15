import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;


public class Global extends GlobalSettings {
	private static GenericDAO dao = new GenericDAOImpl();

	@Override
	public void onStart(Application app) {
		Logger.info("Aplicação inicializada...");

		JPA.withTransaction(new play.libs.F.Callback0() {
			@Override
			public void invoke() throws Throwable {
				Usuario usuario = new Usuario("casal20", "casal20@gmail.com", "casal20");
				if (dao.findByAttributeName("Usuario", "email", "casal20") != null) {
					dao.persist(usuario);
				}
				dao.flush();				
			}
		});
	}
}

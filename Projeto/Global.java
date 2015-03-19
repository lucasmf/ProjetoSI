import models.Dica;
import models.DicaMaterial;
import models.DicaSimples;
import models.Disciplina;
import models.Tema;
import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;


public class Global extends GlobalSettings {
	private static GenericDAO dao = new GenericDAOImpl();

	public void addTemaGlobal(Disciplina disciplina, String nome) {
		if (dao.findByAttributeName("Tema", "nome", nome) != null) {
			Tema tema = new Tema(nome);
			DicaSimples dica = new DicaMaterial("dica");
			dao.persist(dica);
			tema.addDica(dica);
			dao.persist(tema);
			disciplina.addTema(tema);
		}
	}
	
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
				
				Disciplina disciplina = new Disciplina("SI1");
				
				addTemaGlobal(disciplina, "Análise x Design");
				addTemaGlobal(disciplina, "OO");
				addTemaGlobal(disciplina, "GRASP");
				addTemaGlobal(disciplina, "GoF");
				addTemaGlobal(disciplina, "Arquitetura");
				addTemaGlobal(disciplina, "Play");
				addTemaGlobal(disciplina, "JS");
				addTemaGlobal(disciplina, "HTML+CSS+Bootstrap");
				addTemaGlobal(disciplina, "Heroku");
				addTemaGlobal(disciplina, "Labs");
				addTemaGlobal(disciplina, "Minitestes");
				addTemaGlobal(disciplina, "Projeto");
				
				if (dao.findByAttributeName("Disciplina", "nome", "SI1") != null) {
					dao.persist(disciplina);
				}
				dao.flush();				
			}
		});
	}
}

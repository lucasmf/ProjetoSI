package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import models.Dica;
import models.Disciplina;
import models.Metadica;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;

import org.junit.Before;
import org.junit.Test;

import base.AbstractTest;
import play.db.jpa.Transactional;

public class DisciplinaTest extends AbstractTest {
	
	private static GenericDAO dao = new GenericDAOImpl();
	
//	@Before
//	public void setUp() {
//		Disciplina disciplina = new Disciplina("SI");
//		dao.persist(disciplina);
//		dao.flush();
//	}
	
	@Test
	public void deveInicializarDisciplinaCorretamente() {
		Disciplina disciplina = new Disciplina("SI");
		assertEquals(0, disciplina.getMetadicas().size());
		assertEquals(0,disciplina.getTemas().size());
	}
	
	@Test
	public void deveInicializarDBComUmaDisciplina() {
		List <Disciplina> disciplinas = dao.findAllByClassName("Disciplina");
		assertEquals(1, disciplinas.size());
	}
	
	@Test
	public void deveSalvarDisciplinaNoBD() {
		List <Disciplina> disciplinas = dao.findAllByClassName("Disciplina");
		int before = disciplinas.size();
		Disciplina disciplina = new Disciplina("Disciplina1");
		dao.persist(disciplina);
		dao.flush();
		disciplinas = dao.findAllByClassName("Disciplina");
		assertEquals(1, disciplinas.size()-before);
	}
	
	@Test
	public void deveOrdenarMetadicasCorretamente() {
		Disciplina disciplina = new Disciplina("SI");
		disciplina.setId((long) 1);
		
		Metadica metadica1 = new Metadica("1", new ArrayList<Dica>());
		metadica1.votar((long) 1, 1);
		metadica1.votar((long) 1, 0);
		
		Metadica metadica2 = new Metadica("2", new ArrayList<Dica>());
		metadica2.votar((long) 1, 1);
		metadica2.votar((long) 2, 1);
		
		Metadica metadica3 = new Metadica("3", new ArrayList<Dica>());
		metadica3.votar((long) 1, 0);
		disciplina.addMetadica(metadica1);
		disciplina.addMetadica(metadica2);
		disciplina.addMetadica(metadica3);
		
		disciplina.sortMetadicas();
		List<Metadica> metadicas = disciplina.getMetadicas();
		assertEquals("2", metadicas.get(0).getComentario());
		assertEquals("1", metadicas.get(1).getComentario());
		assertEquals("3", metadicas.get(2).getComentario());
	}
	
 }

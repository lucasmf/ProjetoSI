package tests;

import static org.junit.Assert.*;

import java.util.Map;

import models.Dica;
import models.DicaAssunto;
import models.Usuario;

import org.junit.Before;
import org.junit.Test;

public class DicaTest {
	Usuario usuario1, usuario2, usuario3;
	
	@Before
	public void setUp() {
		usuario1 = new Usuario("nome", "email", "senha");
		usuario1.setId((long) 1);
		
		usuario2 = new Usuario("nome2", "email2", "senha2");
		usuario2.setId((long) 2);
		
		usuario3 = new Usuario("nome3", "email3", "senha3");
		usuario3.setId((long) 3);
	}
	
	@Test
	public void inicializaDicaTest() {
		Dica dica = new DicaAssunto("Assunto");
		int votos[] = dica.getVotos();
		Integer votosInapropriacao = dica.getVotosInapropiacao();
		Map<Long, Integer> votantes = dica.getVotantes();
		Map<Long, Integer> votantesInapropriacao = dica
				.getVotantesInapropriacao();
		
		assertEquals(2, votos.length);
		assertEquals(0, votos[0]);
		assertEquals(0, votos[1]);
		assertEquals(new Integer(0), votosInapropriacao);
		assertEquals(0, votantes.size());
		assertEquals(0, votantesInapropriacao.size());
	}
	
	@Test
	public void votarNegativoTest() {
		Dica dica = new DicaAssunto("Assunto");
		//Voto negativo = 0
		dica.votar(usuario1.getId(), 0);
		int votos[] = dica.getVotos();
		assertEquals(1, votos[0]);
		assertEquals(0, votos[1]);
		Map<Long, Integer> votantes = dica.getVotantes();
		assertEquals(1, votantes.size());
	}
	
	@Test
	public void votarPositivoTest() {
		Dica dica = new DicaAssunto("Assunto");
		//Voto Positivo = 1
		dica.votar(usuario1.getId(), 1);
		int votos[] = dica.getVotos();
		assertEquals(1, votos[1]);
		assertEquals(0, votos[0]);
		Map<Long, Integer> votantes = dica.getVotantes();
		assertEquals(1, votantes.size());
	}
	
	
	@Test
	public void votarInapropriacaoTest() {
		Dica dica = new DicaAssunto("Assunto");
		//Voto Inapropriacao = 2
		dica.votar(usuario1.getId(), 2);
		Map<Long, Integer> votantes = dica.getVotantes();
		assertEquals(0, votantes.size());
		Map<Long, Integer> votantesInapropriacao = dica
				.getVotantesInapropriacao();
		assertEquals(1, votantesInapropriacao.size());
		Integer votosInapropriacao = dica.getVotosInapropiacao();
		assertEquals(new Integer(1), votosInapropriacao);
	}
	
	@Test
	public void isApropriadoTest() {
		Dica dica = new DicaAssunto("Assunto");
		dica.votar(usuario1.getId(), 2);
		assertTrue(dica.isApropriada());
		dica.votar(usuario2.getId(), 2);
		assertTrue(dica.isApropriada());
		dica.votar(usuario3.getId(), 2);
		assertFalse(dica.isApropriada());
	}
	
	@Test
	public void naoPodeVotar2VezesTest() {
		Dica dica = new DicaAssunto("Assunto");
		//Voto negativo = 0
		dica.votar(usuario1.getId(), 0);
		dica.votar(usuario1.getId(), 0);
		int votos[] = dica.getVotos();
		assertEquals(1, votos[0]);
		assertEquals(0, votos[1]);
		Map<Long, Integer> votantes = dica.getVotantes();
		assertEquals(1, votantes.size());
	}
	
	@Test
	public void naoPodevotar2VezesInapropriacaoTest() {
		Dica dica = new DicaAssunto("Assunto");
		//Voto Inapropriacao = 2
		dica.votar(usuario1.getId(), 2);
		dica.votar(usuario1.getId(), 2);
		Map<Long, Integer> votantes = dica.getVotantes();
		assertEquals(0, votantes.size());
		Map<Long, Integer> votantesInapropriacao = dica
				.getVotantesInapropriacao();
		assertEquals(1, votantesInapropriacao.size());
		Integer votosInapropriacao = dica.getVotosInapropiacao();
		assertEquals(new Integer(1), votosInapropriacao);
	}
	
	@Test
	public void testCompareTo() {
		Dica dica1 = new DicaAssunto("Assunto");
		Dica dica2 = new DicaAssunto("Assunto");
		
		assertEquals(0, dica1.compareTo(dica2));
		
		dica1.votar(usuario1.getId(), 1);
		assertEquals(-1, dica1.compareTo(dica2));
		
		dica1.votar(usuario2.getId(), 0);
		dica2.votar(usuario1.getId(), 1);
		assertEquals(1, dica1.compareTo(dica2));
	}
	
	@Test
	public void podeVotarNoMax20VezesTest() {
		Dica dica = new DicaAssunto("Assunto");
		for(int i = 0; i  < 22; i++) {
			dica.votar((long)i, 1);
		}
		int votos[] = dica.getVotos();
		assertEquals(0, votos[0]);
		assertEquals(20, votos[1]);
		Map<Long, Integer> votantes = dica.getVotantes();
		assertEquals(20, votantes.size());
	}
}

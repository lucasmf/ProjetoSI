package tests;

import static org.junit.Assert.*;

import java.util.Map;

import models.Dica;
import models.DicaAssunto;
import models.Usuario;

import org.junit.Before;
import org.junit.Test;

public class DicaTest {
	Usuario usuario;
	
	@Before
	public void setUp() {
		usuario = new Usuario("nome", "email", "senha");
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
		dica.votar(usuario.getId(), 0);
		int votos[] = dica.getVotos();
		assertEquals(1, votos[0]);
		assertEquals(0, votos[1]);
		Map<Long, Integer> votantes = dica.getVotantes();
		assertEquals(1, votantes.size());
	}
	
	@Test
	public void votarPositivoTest() {
		Dica dica = new DicaAssunto("Assunto");
		//Voto negativo = 0
		dica.votar(usuario.getId(), 1);
		int votos[] = dica.getVotos();
		assertEquals(1, votos[1]);
		assertEquals(0, votos[0]);
		Map<Long, Integer> votantes = dica.getVotantes();
		assertEquals(1, votantes.size());
	}
}

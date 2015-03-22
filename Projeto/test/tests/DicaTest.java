package tests;

import java.util.Map;

import models.Dica;
import models.DicaAssunto;

import org.junit.Test;

public class DicaTest {

	@Test
	public void inicializaDica() {
		Dica dica = new DicaAssunto("Assunto");
		int votos[] = dica.getVotos();
		Integer votosInapropriacao = dica.getVotosInapropiacao();
		Map<Long, Integer> votantes = dica.getVotantes();
		Map<Long, Integer> votantesInapropriacao = dica
				.getVotantesInapropriacao();
	}
}

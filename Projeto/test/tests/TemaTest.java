package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import models.DicaSimples;
import models.Tema;
import models.Usuario;

import org.junit.Before;
import org.junit.Test;

public class TemaTest {

	public static Usuario usuario1, usuario2, usuario3;
	
	@Before
	public void setUp() throws Exception {
		usuario1 = new Usuario();
		usuario2 = new Usuario();
		usuario3 = new Usuario();
		usuario1.setId(new Long(1));
		usuario2.setId(new Long(2));
		usuario3.setId(new Long(3));
	}
	
	@Test
	public void testIncializaTema() {
		Tema tema = new Tema("tema");
		Map <Long, Integer> votos = tema.getVotos();
		Map <Integer, Integer> quantidadeVotos = tema.getQuantidadeVotos();
		List<DicaSimples> dicas = tema.getDicas();
		
		assertEquals(0, dicas.size());
		assertEquals(true, votos.isEmpty());
		assertEquals(5, quantidadeVotos.size());
		assertEquals(new Integer(0), quantidadeVotos.get(-2));
		assertEquals(new Integer(0), quantidadeVotos.get(-1));
		assertEquals(new Integer(0), quantidadeVotos.get(0));
		assertEquals(new Integer(0), quantidadeVotos.get(1));
		assertEquals(new Integer(0), quantidadeVotos.get(2));
	}
	
	@Test
	public void testVotar1() {
		Tema tema = new Tema("tema");
		assertEquals(0.0, tema.getMedia(), 1e-5);
		assertEquals(0.0, tema.getMediana(), 1e-5);
		tema.votar(usuario1, -2);
		assertEquals(-2.0, tema.getMedia(), 1e-5);
		assertEquals(-2.0, tema.getMediana(), 1e-5);
		tema.votar(usuario1, -1);
		assertEquals(-1.0, tema.getMedia(), 1e-5);
		assertEquals(-1.0, tema.getMediana(), 1e-5);	
	}
	
	@Test
	public void testVotar2() {
		Tema tema = new Tema("tema");
		tema.votar(usuario1, 2);
		assertEquals(2.0, tema.getMedia(), 1e-5);
		assertEquals(2.0, tema.getMediana(), 1e-5);
		tema.votar(usuario2, -2);
		assertEquals(0.0, tema.getMedia(), 1e-5);
		assertEquals(0.0, tema.getMediana(), 1e-5);
		tema.votar(usuario2, 1);
		assertEquals(1.5, tema.getMedia(), 1e-5);
		assertEquals(1.5, tema.getMediana(), 1e-5);
	}
	
	public void testGetMediana() {
		Tema tema = new Tema("tema");
		tema.votar(usuario1, 2);
		assertEquals(2.0, tema.getMediana(), 1e-5);
		tema.votar(usuario2, -2);
		assertEquals(0.0, tema.getMediana(), 1e-5);
		tema.votar(usuario3, 1);
		assertEquals(1, tema.getMediana(), 1e-5);
		tema.votar(usuario3, 2);
		assertEquals(2, tema.getMediana(), 1e-5);
	}
	
	public void testGetMedia() {
		Tema tema = new Tema("tema");
		tema.votar(usuario1, 2);
		assertEquals(2.0, tema.getMedia(), 1e-5);
		tema.votar(usuario2, -2);
		assertEquals(0.0, tema.getMedia(), 1e-5);
		tema.votar(usuario3, 1);
		assertEquals(1.0/3, tema.getMedia(), 1e-5);
		tema.votar(usuario3, 2);
		assertEquals(2.0/3, tema.getMedia(), 1e-5);
	}
	

}

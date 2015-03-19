package models;

import java.util.*;

import models.Usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import scala.collection.mutable.HashSet;

@Entity(name="Tema")
public class Tema {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	private HashMap<Long, Integer> votos;
	private HashMap<Integer, Integer> quantidadeVotos;
	//private String glyphicon;
	
	@ManyToOne
	private Disciplina disciplina;
		
	public Tema() {
		
	}
	
	
	
	public Tema(String nome) {
		votos = new HashMap<Long, Integer>();
		quantidadeVotos = new HashMap<Integer, Integer>();
		this.nome = nome;
	}
	
	
	public void votar(Usuario usuario, int v) {
		Integer votoATrocar = votos.get(usuario.getId());
		votos.put(usuario.getId(), v);
		if(votoATrocar != null) {
			quantidadeVotos.put(votoATrocar, quantidadeVotos.get(votoATrocar)-1);
		}
		quantidadeVotos.put(v, quantidadeVotos.get(v)+1);
	}
	
	public Integer getMediana() {
		List<Integer> list = new ArrayList<Integer>();
		for(Integer i = -2; i<=2; i++) {
			for(Integer j = 0; j<quantidadeVotos.get(i); j++) {
				list.add(i);
			}
		}
		if(list.size()%2 == 0) {
			return (list.get(list.size()/2) + list.get((list.size()/2)+1))/2;
		}
		return list.get(list.size()/2);
	}
	
	public Integer getMedia() {
		Integer ret = 0;
		Integer total = 0;
		for(Integer i = -2; i<=2; i++) {
			ret += quantidadeVotos.get(i)*i;
			total += quantidadeVotos.get(i);
		}
		return ret/total;
	}
	
	public HashMap<Integer, Integer> getQuantidadeVotos() {
		return quantidadeVotos;
	}

	public void setQuantidadeVotos(HashMap<Integer, Integer> quantidadeVotos) {
		this.quantidadeVotos = quantidadeVotos;
	}
	
	public HashMap<Long, Integer> getVotos() {
		return votos;
	}

	public void setVotos(HashMap<Long, Integer> votos) {
		this.votos = votos;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
/*	public String getGlyphicon() {
		return glyphicon;
	}
	public void setGlyphicon(String glyphicon) {
		this.glyphicon = glyphicon;
	}*/
}

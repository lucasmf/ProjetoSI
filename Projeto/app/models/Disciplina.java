package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="Disciplina")
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String nome;
	//private String sigla; ??
	@OneToMany
	private List<Tema> temas = new ArrayList<Tema>();
	
	@OneToMany
	private List<Metadica> metadicas = new ArrayList<Metadica>();
	
	
	public Disciplina() {
		
	}
	
	public Disciplina(String nome) {
		this.nome = nome;
	}
	
	public List<Tema> getTemas() {
		return temas;
	}

	public void setTemas(List<Tema> temas) {
		this.temas = temas;
	}
	
	public void addTema(Tema tema) {
		temas.add(tema);
	}

	public List<Metadica> getMetadicas() {
		return metadicas;
	}

	public void setMetadicas(List<Metadica> metadicas) {
		this.metadicas = metadicas;
	}
	
	public void addMetadica(Metadica metadica) {
		metadicas.add(metadica);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}

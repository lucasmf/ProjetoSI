package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.ElementCollection;
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
	
	@OneToMany
	@ElementCollection
	private List<Tema> temas = new ArrayList<Tema>();
	
	@OneToMany
	@ElementCollection
	private List<Metadica> metadicas = new ArrayList<Metadica>();
	
	
	public Disciplina() {
		
	}
	
	public Disciplina(String nome) {
		this.setNome(nome);
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
	
	@SuppressWarnings("unchecked")
	public void sortMetadicas() {
		Collections.sort(this.metadicas);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}

package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="Tema")
public class Tema {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	//private String glyphicon;
	
	@ManyToOne
	private Disciplina disciplina;
	
	public Tema() {
		
	}
	
	public Tema(String nome) {
		this.nome = nome;
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

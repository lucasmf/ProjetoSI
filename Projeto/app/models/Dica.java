package models;

import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name="Dica")
public abstract class Dica {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Disciplina disciplina;
	
	private HashMap<Long, Avaliacao> avaliacoes;
	
	public Dica() {
		
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public void addAvaliacao(Usuario usuario, Avaliacao avaliacao) {
		
	}

	public HashMap<Long, Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(HashMap<Long, Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
}

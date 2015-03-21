package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity(name="Dica")
public abstract class Dica implements Comparable{

	@Id
	@GeneratedValue
	private Long id;

	private int votos[];
	
	@ManyToOne(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Disciplina disciplina;
	
	//private HashMap<Long, Avaliacao> avaliacoes;
	
	public Dica() {
		this.votos = new int[2];
		votos[0] = 0;
		votos[1] = 0;
	}
	
	public double getAprovacao(){
		if(votos[0]+votos[1] == 0) return 0.0;
		return (double)votos[1]/(votos[0]+votos[1]);
	}
	
	public int getVotosNegavitos() {
		return votos[0];
	}
	
	public int getVotosPositivos() {
		return votos[1];
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	public void votar(int v) {
		votos[v]++;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public int[] getVotos() {
		return votos;
	}

	public void setVotos(int votos[]) {
		this.votos = votos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public int compareTo(Object o) {
		if(o.getClass() != Dica.class) return -1;
		Dica other = (Dica)o;
		return Double.compare(this.getAprovacao(), other.getAprovacao());
	}
	
/*	public void addAvaliacao(Usuario usuario, Avaliacao avaliacao) {
		
	}

	public HashMap<Long, Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(HashMap<Long, Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}*/
}

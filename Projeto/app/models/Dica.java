package models;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	
	private Integer votosInapropriacao = 0;
	
	@ElementCollection
	private Map <Long, Integer> votantes, votantesInapropriacao;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Disciplina disciplina;
	
	//private HashMap<Long, Avaliacao> avaliacoes;
	
	protected String color;
	
	protected String backgroundColor;
	
	public String getColor() {
		return "";
	}
	
	public String getBackgroundColor() {
		return "";
	}
	
	public void setBackGroundColor(String color) {
		this.backgroundColor = color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public Dica() {
		this.votos = new int[2];
		this.votantes = new TreeMap<Long, Integer>();
		this.votantesInapropriacao = new TreeMap<Long, Integer>();
		this.votosInapropriacao = new Integer(0);
		votos[0] = 0;
		votos[1] = 0;
	}
	
	public int getAprovacao(){
		if(votos[0]+votos[1] == 0) return 0;
		return (votos[1]*100/(votos[0]+votos[1]));
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
	
	private int quantidadeDeVotos() {
		return votos[0]+votos[1];
	}
	
	public void votar(Long id, int v) {
		
		if(v == 2) {
			if(votosInapropriacao == null) votosInapropriacao = new Integer(0);
			if(votantesInapropriacao.get(id) == null) {
				votosInapropriacao++;
				votantesInapropriacao.put(id, 2);
			}
			return;
		}
	
		
		if(quantidadeDeVotos() >= 20) return;
		
		if(votantes.get(id) != null) {
			votos[votantes.get(id)]--;
		}
		votantes.put(id, v);
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
		Dica other = (Dica)o;
		return Integer.compare(other.getAprovacao(), this.getAprovacao());
	}

	public Integer getVotosInapropiacao() {
		return votosInapropriacao;
	}

	public void setVotosInapropiacao(Integer votosInapropiacao) {
		this.votosInapropriacao = votosInapropiacao;
	}

	public boolean isApropriada() {
		if(votosInapropriacao == null) votosInapropriacao = new Integer(0);
		return votosInapropriacao < 3;
	}
	
//	public int getVotosInapropiacao() {
//		return votosInapropiacao;
//	}
//
//	public void setVotosInapropiacao(int votosInapropiacao) {
//		this.votosInapropiacao = votosInapropiacao;
//	}

	
	
/*	public void addAvaliacao(Usuario usuario, Avaliacao avaliacao) {
		
	}

	public HashMap<Long, Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(HashMap<Long, Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}*/
}

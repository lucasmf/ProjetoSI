package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Dica")
public abstract class Dica implements Comparable {

	public final static int LIMITE_VOTOS = 20;
	public final static int LIMITE_VOTOS_INAPROPRIADOS = 3;
	public final static int VOTO_INAPROPRIADO = 2;
	public final static int VOTO_POSITIVO = 1;
	public final static int VOTO_NEGATIVO = 0;

	@Id
	@GeneratedValue
	private Long id;

	private int votos[];

	private Integer votosInapropriacao = 0;

	@ElementCollection
	private List<String> comentarios;

	@ElementCollection
	private Map<Long, Integer> votantes, votantesInapropriacao;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Disciplina disciplina;

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
		this.comentarios = new ArrayList<String>();
		votos[VOTO_NEGATIVO] = 0;
		votos[VOTO_POSITIVO] = 0;
	}

	public int getAprovacao() {
		if (quantidadeDeVotos() == 0)
			return 0;
		return (votos[VOTO_POSITIVO] * 100 / (quantidadeDeVotos()));
	}

	public int getVotosNegavitos() {
		return votos[VOTO_NEGATIVO];
	}

	public int getVotosPositivos() {
		return votos[VOTO_POSITIVO];
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	private int quantidadeDeVotos() {
		return votos[VOTO_NEGATIVO] + votos[VOTO_POSITIVO];
	}

	public void votar(Long id, int v) {

		if (v == VOTO_INAPROPRIADO) {
			if (votosInapropriacao == null)
				votosInapropriacao = new Integer(0);
			if (getVotantesInapropriacao().get(id) == null) {
				votosInapropriacao++;
				getVotantesInapropriacao().put(id, 2);
			}
			return;
		}

		if (quantidadeDeVotos() >= LIMITE_VOTOS)
			return;

		if (votantes.get(id) != null) {
			votos[getVotantes().get(id)]--;
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
		Dica other = (Dica) o;
		int ret = Integer.compare(other.getAprovacao(), this.getAprovacao());
		if(ret == 0) {
			if(other.getId() != null && this.getId() != null) {
				return Long.compare(other.getId(), this.getId());
			}
		}
		return ret;
	}

	public Integer getVotosInapropiacao() {
		return votosInapropriacao;
	}

	public void setVotosInapropiacao(Integer votosInapropiacao) {
		this.votosInapropriacao = votosInapropiacao;
	}

	public boolean isApropriada() {
		if (votosInapropriacao == null)
			votosInapropriacao = new Integer(0);
		return votosInapropriacao < LIMITE_VOTOS_INAPROPRIADOS;
	}

	public Map<Long, Integer> getVotantes() {
		return votantes;
	}

	public void setVotantes(Map<Long, Integer> votantes) {
		this.votantes = votantes;
	}

	public Map<Long, Integer> getVotantesInapropriacao() {
		return votantesInapropriacao;
	}

	public void setVotantesInapropriacao(
			Map<Long, Integer> votantesInapropriacao) {
		this.votantesInapropriacao = votantesInapropriacao;
	}

	public boolean usuarioPodeComentar(Long id) {
		if (votantes.get(id) == null)
			return false;
		return votantes.get(id).equals(0);
	}

	public void addComentario(String comentario) {
		this.comentarios.add(comentario);
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}

	public abstract String toString();
}

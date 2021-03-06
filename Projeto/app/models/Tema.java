package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "Tema")
public class Tema {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;
	
	@ElementCollection
	private Map <Long, Integer> votos;
	
	@ElementCollection
	private Map <Integer, Integer> quantidadeVotos;

	@OneToMany
	private List<DicaSimples> dicas = new LinkedList<DicaSimples>();

	@ManyToOne
	private Disciplina disciplina;

	public Tema() {

	}

	public Tema(String nome) {
		votos = new TreeMap<Long, Integer>();
		quantidadeVotos = new TreeMap<Integer, Integer>();
		this.nome = nome;
		quantidadeVotos.put(0, 0);
		quantidadeVotos.put(1, 0);
		quantidadeVotos.put(2, 0);
		quantidadeVotos.put(-1, 0);
		quantidadeVotos.put(-2, 0);
	}

	public void addDica(DicaSimples dica) {
		dicas.add(dica);
	}

	public void votar(Usuario usuario, int v) {
		Integer votoATrocar = votos.get(usuario.getId());
		votos.put(usuario.getId(), v);
		if (votoATrocar != null) {
			quantidadeVotos.put(votoATrocar,
					quantidadeVotos.get(votoATrocar) - 1);
		}
		quantidadeVotos.put(v, quantidadeVotos.get(v) + 1);
	}

	public Double getMediana() {
		List<Integer> list = new ArrayList<Integer>();
		for (Integer i = -2; i <= 2; i++) {
			for (Integer j = 0; j < quantidadeVotos.get(i); j++) {
				list.add(i);
			}
		}

		if (list.size() == 0)
			return 0.0;

		double ret;
		if (list.size() % 2 == 0)
			ret = ((double) list.get(list.size() / 2).intValue() + (double) list
					.get((list.size() / 2) - 1).intValue()) / 2.0;
		else
			ret = (double) list.get(list.size() / 2).intValue();
		return ret;
	}

	public Double getMedia() {
		double ret = 0;
		Integer total = 0;
		for (Integer i = -2; i <= 2; i++) {
			ret += quantidadeVotos.get(i).intValue() * (double) i;
			total += quantidadeVotos.get(i);
		}
		if (total.equals(0))
			return 0.0;
		return ret / total;
	}

	public Map<Integer, Integer> getQuantidadeVotos() {
		return quantidadeVotos;
	}

	public void setQuantidadeVotos(Map<Integer, Integer> quantidadeVotos) {
		this.quantidadeVotos = quantidadeVotos;
	}

	public Map<Long, Integer> getVotos() {
		return votos;
	}

	public void setVotos(Map<Long, Integer> votos) {
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

	public List<DicaSimples> getDicas() {
		return dicas;
	}

	public void setDicas(List<DicaSimples> dicas) {
		this.dicas = dicas;
	}

	@SuppressWarnings("unchecked")
	public void sortDicas() {
		Collections.sort(this.dicas);
	}
}

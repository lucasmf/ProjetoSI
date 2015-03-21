package models;

import javax.persistence.Entity;

@Entity(name="DicaDisciplina")
public class DicaDisciplina extends DicaSimples {
	private String razao;
	private String nomeDisciplina;

	public DicaDisciplina() {
		
	}
	
	public DicaDisciplina(String nomeDisciplina, String razao) {
		this.nomeDisciplina = nomeDisciplina;
		this.razao = razao;
		
	}

	@Override
	public String toString() {
		return "Disciplina que vai te ajudar: " + nomeDisciplina +
		". Razão :" + razao ;
	}

}

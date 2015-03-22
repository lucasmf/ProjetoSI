package models;

import javax.persistence.Entity;

@Entity(name="DicaDisciplina")
public class DicaDisciplina extends DicaSimples {
	private static final String BACKGROUND_COLOR = "rgba(255, 0, 0, 0.2)";
	private static final String COLOR = "red";
	private String razao;
	private String nomeDisciplina;

	public DicaDisciplina() {
		super();
	}
	
	public DicaDisciplina(String nomeDisciplina, String razao) {
		super();
		this.nomeDisciplina = nomeDisciplina;
		this.razao = razao;
	}

	public String getColor() {
		return COLOR;
	}
	
	public String getBackgroundColor() {
		return BACKGROUND_COLOR;
	}	
	
	@Override
	public String toString() {
		return "Disciplina que vai te ajudar: " + nomeDisciplina +
		". Razão :" + razao ;
	}

}

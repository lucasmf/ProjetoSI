package models;

import javax.persistence.Entity;

@Entity(name="DicaAssunto")
public class DicaAssunto extends DicaSimples {
	
	private String assunto;
	
	public DicaAssunto() {
		
	}
	
	public DicaAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	public String getColor() {
		return "orange";
	}
	
	public String getBackgroundColor() {
		return "rgba(255, 102, 0, 0.2)";
	}	
	
	@Override
	public String toString() {
		return "Assunto que você precisa saber: " + assunto;
	}

}

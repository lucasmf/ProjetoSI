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
	
	@Override
	public String toString() {
		return "Assunto que você precisa saber: " + assunto;
	}

}

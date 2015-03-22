package models;

import javax.persistence.Entity;

@Entity(name="DicaAssunto")
public class DicaAssunto extends DicaSimples {
	
	private static final String BACKGROUND_COLOR = "rgba(255, 102, 0, 0.2)";
	private static final String COLOR = "orange";
	private String assunto;
	
	public DicaAssunto() {
		super();
	}
	
	public DicaAssunto(String assunto) {
		super();
		this.assunto = assunto;
	}
	
	public String getColor() {
		return COLOR;
	}
	
	public String getBackgroundColor() {
		return BACKGROUND_COLOR;
	}	
	
	@Override
	public String toString() {
		return "Assunto que você precisa saber: " + assunto;
	}

}

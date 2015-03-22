package models;

import javax.persistence.Entity;

@Entity(name="DicaConselho")
public class DicaConselho extends DicaSimples {
	private static final String BACKGROUND_COLOR = "rgba(0, 0, 255, 0.2)";
	private static final String COLOR = "blue";
	private String conselho;

	public DicaConselho() {
		super();
	}
	
	public DicaConselho(String conselho) {
		super();
		this.conselho = conselho;
	}
	
	public String getColor() {
		return COLOR;
	}
	
	public String getBackgroundColor() {
		return BACKGROUND_COLOR;
	}	
	
 	@Override
	public String toString() {
		return "Conselho: " + conselho;
	}

}

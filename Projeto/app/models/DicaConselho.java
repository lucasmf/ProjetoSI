package models;

import javax.persistence.Entity;

@Entity(name="DicaConselho")
public class DicaConselho extends DicaSimples {
	private static final String BLUE = "blue";
	private String conselho;

	public DicaConselho() {
		super();
	}
	
	public DicaConselho(String conselho) {
		super();
		this.conselho = conselho;
	}
	
	public String getColor() {
		return "blue";
	}
	
	public String getBackgroundColor() {
		return "rgba(0, 0, 255, 0.2)";
	}	
	
 	@Override
	public String toString() {
		return "Conselho: " + conselho;
	}

}

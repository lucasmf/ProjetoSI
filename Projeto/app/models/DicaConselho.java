package models;

import javax.persistence.Entity;

@Entity(name="DicaConselho")
public class DicaConselho extends DicaSimples {
	private String conselho;

	public DicaConselho() {
		
	}
	
	public DicaConselho(String conselho) {
		this.conselho = conselho;
		
		
	}
	
 	@Override
	public String toString() {
		return "Conselho: " + conselho;
	}

}

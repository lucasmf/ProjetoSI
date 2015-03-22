package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name="Metadica")
public class Metadica extends Dica {
	
	@OneToMany
	private List<Dica> dicasAgregadas;
	private String dica;
	
	public Metadica() {
		
	}
	
	public Metadica(String dica, List<Dica> dicasAgregadas) {
		this.setDica(dica);
		this.setDicasAgregadas(dicasAgregadas);
		
	}

	public List<Dica> getDicasAgregadas() {
		return dicasAgregadas;
	}

	public void setDicasAgregadas(List<Dica> dicasAgregadas) {
		this.dicasAgregadas = dicasAgregadas;
	}

	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}
}

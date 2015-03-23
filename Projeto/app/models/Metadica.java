package models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name="Metadica")
public class Metadica extends Dica {
	
	@ElementCollection
	private List<Dica> dicasAgregadas;
	private String comentario;

	
	public Metadica() {
		super();
	}
	
	public Metadica(String comentario, List<Dica> dicasAgregadas) {
		super();
		this.setComentario(comentario);
		this.setDicasAgregadas(dicasAgregadas);
		
	}

	public List<Dica> getDicasAgregadas() {
		return dicasAgregadas;
	}

	public void setDicasAgregadas(List<Dica> dicasAgregadas) {
		this.dicasAgregadas = dicasAgregadas;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	@Override
	public String toString() {
		return "Comentario: " + comentario;
	}
}

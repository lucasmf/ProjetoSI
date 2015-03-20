package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="Avaliacao")
public class Avaliacao {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String comentario;
	private boolean like;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	private Dica dica;
	
	public Avaliacao() {
		
	}
	
	public Avaliacao(boolean like, String comentario, Dica dica) {
		this.setLike(like);
		this.setComentario(comentario);
		this.setDica(dica);
		
	}
	
	public Avaliacao(boolean like) {
		this.setLike(like);
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	public Dica getDica() {
		return dica;
	}

	public void setDica(Dica dica) {
		this.dica = dica;
	}
}

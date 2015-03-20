package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Avaliacao")
public class Avaliacao {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String comentario;
	private boolean like;
	
	public Avaliacao() {
		
	}
	
	public Avaliacao(boolean like, String comentario) {
		this.setLike(like);
		this.setComentario(comentario);
		
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
}

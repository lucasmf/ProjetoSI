package models;

public class Comentario {
	private String comentario;

	public Comentario() {
		
	}
	
	public Comentario(String comentario) {
		this.setComentario(comentario);
		
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}

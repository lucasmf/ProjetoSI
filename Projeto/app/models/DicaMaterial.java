package models;

import javax.persistence.Entity;

@Entity(name="DicaMaterial")
public class DicaMaterial extends DicaSimples {
	private static final String BACKGROUND_COLOR = "rgba(0, 255, 0, 0.2)";
	private static final String COLOR = "green";
	private String linkMaterial;
	
	public DicaMaterial() {
		super();
		
	}
	
	public DicaMaterial(String link) {
		super();
		this.linkMaterial = link;
		
	}

	public String getColor() {
		return COLOR;
	}
	
	public String getBackgroundColor() {
		return BACKGROUND_COLOR;
	}
	
	
	public String getLinkMaterial() {
		return linkMaterial;
	}

	public void setLinkMaterial(String linkMaterial) {
		this.linkMaterial = linkMaterial;
	}
	
	@Override
	public String toString() {
		return "Link para Material: " + linkMaterial;
	}

}

package models;

import javax.persistence.Entity;

@Entity(name="DicaMaterial")
public class DicaMaterial extends DicaSimples {
	private String linkMaterial;
	
	public DicaMaterial() {
		super();
		
	}
	
	public DicaMaterial(String link) {
		super();
		this.linkMaterial = link;
		
	}

	public String getColor() {
		return "green";
	}
	
	public String getBackgroundColor() {
		return "rgba(0, 255, 0, 0.2)";
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

package models;

import javax.persistence.Entity;

@Entity(name="DicaMaterial")
public class DicaMaterial extends DicaSimples {
	private String linkMaterial;
	
	public DicaMaterial() {
		
	}
	
	public DicaMaterial(String link) {
		this.linkMaterial = link;
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

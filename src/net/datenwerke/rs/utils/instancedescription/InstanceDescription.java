package net.datenwerke.rs.utils.instancedescription;

public class InstanceDescription {

	private String title;
	private String description;
	private String icon;
	private String objectType;
	
	public InstanceDescription(){
		
	}
	
	public InstanceDescription(String objectType, String title, String description, String icon) {
		this.objectType = objectType;
		this.title = title;
		this.description = description;
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectType() {
		return objectType;
	}
	
	
}

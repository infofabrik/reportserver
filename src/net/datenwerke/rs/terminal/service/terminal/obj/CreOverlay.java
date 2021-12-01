package net.datenwerke.rs.terminal.service.terminal.obj;

import java.util.HashMap;
import java.util.Map;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.terminal.client.terminal.dto"
)
public class CreOverlay extends CommandResultExtension {

	@ExposeToClient
	private String name;
	
	@ExposeToClient
	private String text;
	
	@ExposeToClient
	private Map<String, String> cssProperties = new HashMap<String, String>();
	
	@ExposeToClient
	private boolean remove = false;

	public CreOverlay(){
	}

	public CreOverlay(String name, boolean remove){
		this.name = name;
		this.setRemove(remove);
	}
	
	public CreOverlay(String name, String text){
		this.name = name;
		this.text = text;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, String> getCssProperties() {
		return cssProperties;
	}

	public void setCssProperties(Map<String, String> cssProperties) {
		this.cssProperties = cssProperties;
	}
	
	public void addProperty(String key, String value){
		cssProperties.put(key, value);
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public boolean isRemove() {
		return remove;
	}
	
}

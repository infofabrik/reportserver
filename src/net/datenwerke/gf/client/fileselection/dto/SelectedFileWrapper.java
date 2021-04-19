package net.datenwerke.gf.client.fileselection.dto;

import net.datenwerke.gxtdto.client.dtomanager.Dto;

public class SelectedFileWrapper extends Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5958665141713618455L;

	private String name;
	private String id;
	private Dto originalDto;
	private String type; 
	
	public SelectedFileWrapper(){
	}
	
	public SelectedFileWrapper(String type, Dto dto, String name) {
		setType(type);
		setOriginalDto(dto);
		setName(name);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Dto getOriginalDto() {
		return originalDto;
	}
	public void setOriginalDto(Dto originalDto) {
		this.originalDto = originalDto;
	}
	
	
}

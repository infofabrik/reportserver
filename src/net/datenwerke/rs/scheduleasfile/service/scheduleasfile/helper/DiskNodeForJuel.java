package net.datenwerke.rs.scheduleasfile.service.scheduleasfile.helper;

import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;

public class DiskNodeForJuel {

	private String name = "";
	private String description = "";
	private Long id = -1l;
	
	public DiskNodeForJuel(AbstractTsDiskNode space){
		if(null != space){
			if(null != space.getName())
				name = space.getName();
			if(null != space.getDescription())
				description = space.getDescription();
			if(null != space.getId())
				id = space.getId();
		}
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}
	
	
}

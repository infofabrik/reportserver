package net.datenwerke.rs.teamspace.service.teamspace.helper;

import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;

public class TeamSpaceForJuel {

	private String name = "";
	private String description = "";
	private Long id = -1l;
	
	public TeamSpaceForJuel(TeamSpace space){
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

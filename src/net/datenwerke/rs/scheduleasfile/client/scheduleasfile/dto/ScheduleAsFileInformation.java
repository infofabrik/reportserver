package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto;

import net.datenwerke.rs.scheduler.client.scheduler.dto.AdditionalScheduleInformation;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;

public class ScheduleAsFileInformation implements AdditionalScheduleInformation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2881771133985741640L;

	
	private String name;
	private String description;
	private AbstractTsDiskNodeDto folder;
	private TeamSpaceDto teamSpace;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AbstractTsDiskNodeDto getFolder() {
		return folder;
	}
	public void setFolder(AbstractTsDiskNodeDto folder) {
		this.folder = folder;
	}
	public void setTeamSpace(TeamSpaceDto teamSpace) {
		this.teamSpace = teamSpace;
	}
	public TeamSpaceDto getTeamSpace() {
		return teamSpace;
	}
	
	
}

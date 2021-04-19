package net.datenwerke.rs.tsreportarea.client.tsreportarea.dto;

import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportPostProcessConfigDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;

public class TsDiskPostProcessorConfigDto extends ImportPostProcessConfigDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6431188625314243801L;

	private TeamSpaceDto teamSpace;

	public void setTeamSpace(TeamSpaceDto teamSpace) {
		this.teamSpace = teamSpace;
	}

	public TeamSpaceDto getTeamSpace() {
		return teamSpace;
	}
	
	
}

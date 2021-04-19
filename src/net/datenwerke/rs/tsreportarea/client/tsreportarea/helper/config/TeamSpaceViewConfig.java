package net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.config;

import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;

public class TeamSpaceViewConfig implements ReportViewConfiguration {

	private final TeamSpaceDto teamSpace;

	public TeamSpaceViewConfig(TeamSpaceDto teamSpace) {
		super();
		this.teamSpace = teamSpace;
	}

	public TeamSpaceDto getTeamSpace() {
		return teamSpace;
	}

	
}

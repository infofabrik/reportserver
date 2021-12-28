package net.datenwerke.rs.tsreportarea.service.tsreportarea;

import com.google.inject.Inject;

import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceAppDefinition;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp;

/**
 * 
 *
 */
public class TsDiskTeamSpaceAppDefinition implements TeamSpaceAppDefinition {

	public static final String APP_ID = "tsApp-favoriteReports";
	
	private final TsDiskService diskService;

	@Inject
	public TsDiskTeamSpaceAppDefinition(TsDiskService diskService) {
		this.diskService = diskService;
	}

	@Override
	public String getAppId() {
		return APP_ID;
	}

	@Override
	public String getName() {
		return "Favoriten";
	}

	@Override
	public String getDescription() {
		return "Favoriten";
	}

	@Override
	public void initializeApp(TeamSpace teamSpace, TeamSpaceApp app) {
		diskService.createRoot(teamSpace);
	}

}

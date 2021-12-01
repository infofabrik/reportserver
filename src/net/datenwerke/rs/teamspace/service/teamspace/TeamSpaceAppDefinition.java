package net.datenwerke.rs.teamspace.service.teamspace;

import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp;


public interface TeamSpaceAppDefinition {

	public String getAppId();
	public String getName();
	public String getDescription();
	
	public void initializeApp(TeamSpace teamSpace, TeamSpaceApp app);
	
}

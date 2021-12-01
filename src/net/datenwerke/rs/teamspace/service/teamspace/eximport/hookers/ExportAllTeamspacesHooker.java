package net.datenwerke.rs.teamspace.service.teamspace.eximport.hookers;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.eximport.ex.entity.EntityExportItemConfig;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;

import com.google.inject.Inject;

public class ExportAllTeamspacesHooker implements ExportAllHook {

	private final TeamSpaceService teamSpaceService;
	
	@Inject
	public ExportAllTeamspacesHooker(TeamSpaceService teamSpaceService) {
		this.teamSpaceService = teamSpaceService;
	}

	@Override
	public void configure(ExportConfig config) {
		if(! teamSpaceService.isGlobalTsAdmin())
			throw new ViolatedSecurityException();
		
		for(TeamSpace ts : teamSpaceService.getAllTeamSpaces())
			config.addItemConfig(new EntityExportItemConfig(ts));
	}

}

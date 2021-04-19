package net.datenwerke.rs.teamspace.service.teamspace.eventhandler;

import java.util.Collection;

import com.google.inject.Inject;

import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;

public class UserNodeForceRemoveEventHandler implements EventHandler<ForceRemoveEntityEvent> {

	private final TeamSpaceService tsService;
	
	@Inject
	public UserNodeForceRemoveEventHandler(TeamSpaceService tsService) {
		this.tsService = tsService;
	}

	@Override
	public void handle(ForceRemoveEntityEvent event) {
		AbstractUserManagerNode userNode = (AbstractUserManagerNode) event.getObject();
	
		if(userNode instanceof User){
			User user = (User) userNode;
			
			Collection<TeamSpace> ownedTs = tsService.getOwnedTeamSpaces(user);
			if(null != ownedTs && ! ownedTs.isEmpty()){
				for(TeamSpace teamspace : ownedTs){
					teamspace.setOwner(null);
					tsService.merge(teamspace);
				}
			}
		}
	}
	

}
package net.datenwerke.rs.teamspace.service.teamspace.eventhandler;

import java.util.Collection;
import java.util.Iterator;

import com.google.inject.Inject;

import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceMember;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;

public class UserNodeRemoveEventHandler implements EventHandler<RemoveEntityEvent> {

	private final TeamSpaceService tsService;
	
	@Inject
	public UserNodeRemoveEventHandler(TeamSpaceService tsService) {
		this.tsService = tsService;
	}

	@Override
	public void handle(RemoveEntityEvent event) {
		AbstractUserManagerNode userNode = (AbstractUserManagerNode) event.getObject();
	
		if(userNode instanceof User){
			User user = (User) userNode;
			
			Collection<TeamSpace> ownedTs = tsService.getOwnedTeamSpaces(user);
			if(null != ownedTs && ! ownedTs.isEmpty()){
				Iterator<TeamSpace> it = ownedTs.iterator();
				StringBuilder error = new StringBuilder("User " + user.getUsername() + " is owner of teamspaces. Teamspace Ids: " + it.next().getId());
				while(it.hasNext())
					error.append(", ").append(it.next().getId());
				
				throw new NeedForcefulDeleteException(error.toString());
			}
			
			
		}
		
		Collection<TeamSpace> teamspaces = tsService.getTeamSpacesWithMemberFor(userNode);
		if(null != teamspaces && ! teamspaces.isEmpty()){
			for(TeamSpace teamspace : teamspaces){
				TeamSpaceMember member = tsService.getMemberFor(teamspace,userNode);
				if(null != member){
					teamspace.removeMember(member);
					tsService.remove(member);
				}
			}
		}
	}
	

}

package net.datenwerke.rs.tsreportarea.service.tsreportarea.eventhandler;

import javax.persistence.NoResultException;

import com.google.inject.Inject;

import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class TeamSpaceRemovedCallback implements EventHandler<RemoveEntityEvent> {

	private final TsDiskService favoriteService;
	
	@Inject
	public TeamSpaceRemovedCallback(
		TsDiskService favoriteService	
		){
		
		/* store objects */
		this.favoriteService = favoriteService;
	}
	

	@Override
	public void handle(RemoveEntityEvent event) {
		TeamSpace teamSpace = (TeamSpace) event.getObject();
		
		try{
			TsDiskRoot root = favoriteService.getRoot(teamSpace);
			if(event instanceof ForceRemoveEntityEvent)
				favoriteService.forceRemove(root);
			else
				favoriteService.remove(root);
		} catch(NoResultException e){
			
		}
	}


}

package net.datenwerke.rs.uservariables.service.eventhandler;

import java.util.Collection;

import net.datenwerke.rs.uservariables.service.uservariables.UserVariableService;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

import com.google.inject.Inject;

public class HandleUserNodeRemoveEvents implements EventHandler<RemoveEntityEvent> {

	private final UserVariableService userVarService;

	@Inject
	public HandleUserNodeRemoveEvents(UserVariableService userVarService) {
		this.userVarService = userVarService;
	}

	@Override
	public void handle(RemoveEntityEvent event) {
		AbstractUserManagerNode folk = (AbstractUserManagerNode) event.getObject();

		Collection<UserVariableInstance> instances = userVarService.getDefinedInstancesFor(folk);
		if(null != instances && instances.size() > 0)
			for(UserVariableInstance instance : instances)
				userVarService.remove(instance);
	}

}

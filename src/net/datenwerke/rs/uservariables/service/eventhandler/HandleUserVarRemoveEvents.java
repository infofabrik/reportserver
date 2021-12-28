package net.datenwerke.rs.uservariables.service.eventhandler;

import java.util.Collection;

import com.google.inject.Inject;

import net.datenwerke.rs.uservariables.service.locale.UserVarMessages;
import net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.UserVariableService;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class HandleUserVarRemoveEvents implements EventHandler<RemoveEntityEvent> {

	private final UserVariableService userVarService;

	@Inject
	public HandleUserVarRemoveEvents(UserVariableService userVarService) {
		this.userVarService = userVarService;
	}

	@Override
	public void handle(RemoveEntityEvent event) {
		UserVariableDefinition userVar = (UserVariableDefinition) event.getObject();

		Collection<UserVariableInstance> instances = userVarService.getInstancesForDefinition(userVar);
		if(null != instances && instances.size() > 0)
			throw new NeedForcefulDeleteException(UserVarMessages.INSTANCE.userVarHasInstances());
		
		Collection<UserVariableParameterDefinition> params = userVarService.getParametersFor(userVar);
		if(null != params && params.size() > 0)
			throw new NeedForcefulDeleteException(UserVarMessages.INSTANCE.userVarHasParameters());
	}

}

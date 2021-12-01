package net.datenwerke.rs.uservariables.service.eventhandler;

import java.util.Collection;

import net.datenwerke.rs.core.service.reportmanager.ReportParameterService;
import net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.UserVariableService;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;

import com.google.inject.Inject;

public class HandleUserVarForceRemoveEvents implements EventHandler<ForceRemoveEntityEvent> {

	private final UserVariableService userVarService;
	private final ReportParameterService reportParameterService;

	@Inject
	public HandleUserVarForceRemoveEvents(UserVariableService userVarService, ReportParameterService reportParameterService) {
		this.userVarService = userVarService;
		this.reportParameterService = reportParameterService;
	}

	@Override
	public void handle(ForceRemoveEntityEvent event) {
		UserVariableDefinition userVar = (UserVariableDefinition) event.getObject();

		Collection<UserVariableInstance> instances = userVarService.getInstancesForDefinition(userVar);
		if(null != instances && instances.size() > 0)
			for(UserVariableInstance instance : instances)
				userVarService.remove(instance);
		
		Collection<UserVariableParameterDefinition> params = userVarService.getParametersFor(userVar);
		if(null != params && params.size() > 0){
			for(UserVariableParameterDefinition param : params){
				param.setUserVariableDefinition(null);
				reportParameterService.merge(param);
			}
		}
	}

}

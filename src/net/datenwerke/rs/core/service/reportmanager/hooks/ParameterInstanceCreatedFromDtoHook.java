package net.datenwerke.rs.core.service.reportmanager.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;

/**
 * this should not exist and should be implemented via post processors.
 *
 */
@Deprecated
public interface ParameterInstanceCreatedFromDtoHook extends Hook{
	
	public boolean consumes(ParameterInstanceDto parameterInstanceDto);

	public void posoCreated(ParameterInstanceDto parameterInstanceDto, ParameterInstance parameterInstance);
	

}

package net.datenwerke.rs.core.service.reportmanager.parameters;

import java.util.List;
import java.util.Set;

import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;

public interface ParameterContainerNode {

	public Set<ParameterInstance> getParameterInstances();
	
	public List<ParameterDefinition> getParameterDefinitions();

	public void setParameterDefinitions(List<ParameterDefinition> parameters);
	
	public void addParameterDefinition(ParameterDefinition parameter);
	
	public void removeParameterDefinition(ParameterDefinition paramter);

}

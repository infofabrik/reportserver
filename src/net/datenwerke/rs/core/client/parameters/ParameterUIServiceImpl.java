package net.datenwerke.rs.core.client.parameters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.datenwerke.gf.client.config.ClientConfigService;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 *
 */
public class ParameterUIServiceImpl implements ParameterUIService {

	@SuppressWarnings("unchecked")
	private final Provider<List<ParameterConfigurator>> availableParametersProvider;
	private ClientConfigService clientConfigService;

	@Inject
	public ParameterUIServiceImpl(
		Provider<List<ParameterConfigurator>> availableParameterProvider, 
		ClientConfigService clientConfigService
		){
		
		/* store objects */
		this.availableParametersProvider = availableParameterProvider;
		this.clientConfigService = clientConfigService;
	}
	
	public List<ParameterConfigurator> getAvailableParameterConfigurators(){
		return availableParametersProvider.get();
	}
	
	
	@Override
	public String getMandatoryPrefix(){
		return clientConfigService.getString("mandatoryParameterPrefix", "");
	}
	
	@Override
	public String getMandatorySuffix(){
		return clientConfigService.getString("mandatoryParameterSuffix", "");
	}
	
	@Override
	public String getOptionalPrefix(){
		return clientConfigService.getString("optionalParameterPrefix", "");
	}
	
	@Override
	public String getOptionalSuffix(){
		return clientConfigService.getString("optionalParameterSuffix", "");
	}
	
	
	
	/**
	 * returns the corresponding configurator.
	 * 
	 * @param pd
	 */
	public ParameterConfigurator getConfigurator(ParameterDefinitionDto pd) {
		for(ParameterConfigurator configurator : availableParametersProvider.get())
			if(configurator.consumes(pd.getClass()))
				return configurator;
		
		return null;
	}

	
	@Override
	public ParameterInstanceDto getParameterInstanceFor(Set<ParameterInstanceDto> instances, ParameterDefinitionDto definition){
		if(null == instances)
			return null;
		
		for(ParameterInstanceDto instance : instances){
			if(null == instance.getDefinition())
				throw new IllegalStateException("Instances should always have a definition"); //$NON-NLS-1$
			
			if(instance.getDefinition().equals(definition))
				return instance;
		}
		
		return null;
	}
	
	@Override
	public Collection<ParameterInstanceDto> getRelevantInstancesFor(List<ParameterDefinitionDto> defs, Set<ParameterInstanceDto> instances, ParameterDefinitionDto definition) {
		Collection<ParameterInstanceDto> res = new HashSet<ParameterInstanceDto>();
		if(null != definition.getDependsOn())
			for(ParameterDefinitionDto dependingDefinition : getAllParameterDependents(defs, definition))
				res.add(getParameterInstanceFor(instances, dependingDefinition));
		return res;
	}
	
	@Override
	public List<ParameterDefinitionDto> getAllParameterDependents(List<ParameterDefinitionDto> defs, ParameterDefinitionDto paramDefinition) {
		List<ParameterDefinitionDto> dependents = new ArrayList<ParameterDefinitionDto>();
		
		_getAllParameterDependents(defs, dependents, paramDefinition, paramDefinition);
		
		return dependents;
	}
	
	private void _getAllParameterDependents(List<ParameterDefinitionDto> defs, List<ParameterDefinitionDto> dependents, ParameterDefinitionDto def, ParameterDefinitionDto doNotAdd) {
		def = getRealParameterFromReport(defs, def);
		if(dependents.contains(def))
			return;
		if(doNotAdd != def)
			dependents.add(def);
		
		Iterator<ParameterDefinitionDto> it =  def.getDependsOn().iterator();
		while(it.hasNext())
			_getAllParameterDependents(defs, dependents, it.next(), doNotAdd);
	}
	
	private ParameterDefinitionDto getRealParameterFromReport(List<ParameterDefinitionDto> defs, ParameterDefinitionDto def) {
		for(ParameterDefinitionDto d : defs)
			if(null != d.getId() && d.getId().equals(def.getId()))
				return d;
		return def;
	}
	
	@Override
	public ParameterDefinitionDto getParameterDefinitionByKey(List<ParameterDefinitionDto> defs, String name) {
		if(null == defs)
			return null;
		for(ParameterDefinitionDto def : defs)
			if(name.equals(def.getKey()))
				return def;
		return null;
	}
}

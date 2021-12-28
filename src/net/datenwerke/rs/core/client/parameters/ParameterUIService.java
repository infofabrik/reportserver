package net.datenwerke.rs.core.client.parameters;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;

public interface ParameterUIService {

   public List<ParameterConfigurator> getAvailableParameterConfigurators();

   public ParameterConfigurator getConfigurator(ParameterDefinitionDto pd);

   public String getMandatoryPrefix();

   public String getMandatorySuffix();

   public String getOptionalPrefix();

   public String getOptionalSuffix();

   public ParameterInstanceDto getParameterInstanceFor(Set<ParameterInstanceDto> instances,
         ParameterDefinitionDto definition);

   public Collection<ParameterInstanceDto> getRelevantInstancesFor(List<ParameterDefinitionDto> defs,
         Set<ParameterInstanceDto> instances, ParameterDefinitionDto definition);

   public List<ParameterDefinitionDto> getAllParameterDependents(List<ParameterDefinitionDto> defs,
         ParameterDefinitionDto paramDefinition);

   public ParameterDefinitionDto getParameterDefinitionByKey(List<ParameterDefinitionDto> defs, String name);

}

package net.datenwerke.rs.uservariables.service.parameters;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.uservariables.service.parameters.postprocessor.UserVariableInstance2DtoPostProcessor;
import net.datenwerke.rs.uservariables.service.uservariables.UserVariableService;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;
import net.datenwerke.security.service.usermanager.entities.User;

import org.hibernate.envers.Audited;

import com.google.inject.Inject;

/**
 * 
 *
 */
@Entity
@Table(name="USERVAR_PARAM_INST")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.uservariables.client.parameters.dto",
	createDecorator=true,
	poso2DtoPostProcessors=UserVariableInstance2DtoPostProcessor.class,
	additionalFields={
		@AdditionalField(name="value", type=String.class)
	}
)
public class UserVariableParameterInstance extends ParameterInstance<UserVariableParameterDefinition> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4562050325298290322L;
	
	@Inject
	private static UserVariableService userVariableService;

	@Override
	public Object getSelectedValue(User user) {
		/* get user variable */
		UserVariableParameterDefinition parameterDefinition = (UserVariableParameterDefinition) getDefinition();
		UserVariableDefinition variableDefinition = parameterDefinition.getUserVariableDefinition();
		
		/* find variable instance for current user */
		UserVariableInstance instance = userVariableService.getVariableInstanceForUser(user, variableDefinition);
		
		if(null == instance){
			throw new RuntimeException("Failed retrieving value for UserVariable \"" + variableDefinition.getName() + "\" and user \""+user.getUsername()+"\". Usually that means, the variable was not bound anywhere in the users subtree.");
		}
		
		return instance.getVariableValue();
	}
	
	@Override
	public Object getDefaultValue(User user, ParameterSet parameterSet) {
		return getSelectedValue(user);
	}

	@Override
	protected Class<?> getType() {
		UserVariableParameterDefinition parameterDefinition = (UserVariableParameterDefinition) getDefinition();
		UserVariableDefinition variableDefinition = parameterDefinition.getUserVariableDefinition();
		return variableDefinition.getType();
	}
	
	@Override
	public void parseStringValue(String value) {
		// don't set user variables
	}

	
}

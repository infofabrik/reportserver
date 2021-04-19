package net.datenwerke.rs.uservariables.service.parameters;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.uservariables.client.uservariables.locale.UserVariablesMessages;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;

import org.hibernate.envers.Audited;

/**
 *
 */
@Entity
@Table(name="USERVAR_PARAM_DEF")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.uservariables.client.parameters.dto",
	displayTitle="UserVariablesMessages.INSTANCE.userVariablesParameterText()",
	additionalImports=UserVariablesMessages.class
)
public class UserVariableParameterDefinition extends ParameterDefinition<UserVariableParameterInstance> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7110014518870459228L;
	
	@ExposeToClient
	@ManyToOne(targetEntity=UserVariableDefinition.class)
	private UserVariableDefinition userVariableDefinition;
	
	public void setUserVariableDefinition(UserVariableDefinition userVariableDefinition) {
		this.userVariableDefinition = userVariableDefinition;
	}

	public UserVariableDefinition getUserVariableDefinition() {
		return userVariableDefinition;
	}
	
	@Override
	protected UserVariableParameterInstance doCreateParameterInstance(){
		return new UserVariableParameterInstance();
	}

	@Override
	public Boolean isEditable() {
		return false;
	}
}

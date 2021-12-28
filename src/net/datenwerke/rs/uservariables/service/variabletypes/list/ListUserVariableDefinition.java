package net.datenwerke.rs.uservariables.service.variabletypes.list;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.uservariables.client.uservariables.locale.UserVariablesMessages;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;

/**
 * 
 *
 */
@Entity
@Table(name="LIST_USERVARIABLE_DEF")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.uservariables.client.variabletypes.list.dto",
	displayTitle="UserVariablesMessages.INSTANCE.listVariableText()",
	additionalImports=UserVariablesMessages.class
)
public class ListUserVariableDefinition extends UserVariableDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514324539059075005L;

	@Override
	protected UserVariableInstance doCreateVariableInstance() {
		return new ListUserVariableInstance();
	}

	@Transient
	@Override
	public Class<?> getType() {
		return List.class;
	}

}

package net.datenwerke.rs.uservariables.service.variabletypes.string;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;

/**
 * 
 *
 */
@Entity
@Table(name="STR_USERVARIABLE_INST")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.uservariables.client.variabletypes.string.dto"
)
public class StringUserVariableInstance extends UserVariableInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2419315182953436034L;
	
	
	@ExposeToClient
	private String value;
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public Object getVariableValue() {
		return getValue();
	}
}

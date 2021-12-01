package net.datenwerke.rs.base.service.parameters.separator;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;

import org.hibernate.envers.Audited;

/**
 * 
 *
 */
@Entity
@Table(name="SEP_PARAM_DEF")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.parameters.separator.dto",
	displayTitle="RsMessages.INSTANCE.separatorParameterText()",
	additionalImports=RsMessages.class
)
public class SeparatorParameterDefinition extends ParameterDefinition<SeparatorParameterInstance> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4747333715325728172L;

	@Override
	protected SeparatorParameterInstance doCreateParameterInstance() {
		return new SeparatorParameterInstance();
	}
	
	@Override
    public boolean isSeparator() {
        return true;
    }

}

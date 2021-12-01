package net.datenwerke.rs.base.service.parameters.headline;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;

import org.hibernate.envers.Audited;

/**
 * 
 *
 */
@Entity
@Table(name="HEADLINE_PARAM_INST")
@Audited
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.parameters.headline.dto"
)
public class HeadlineParameterInstance extends ParameterInstance<HeadlineParameterDefinition> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 330691242202913445L;

	@Override
	public Object getSelectedValue(User user) {
		return ((HeadlineParameterDefinition)getDefinition()).getValue();
	}

	@Override
	public Object getDefaultValue(User user, ParameterSet parameterSet) {
		return getSelectedValue(user);
	}

	@Override
	protected Class<?> getType() {
		return String.class;
	}


}

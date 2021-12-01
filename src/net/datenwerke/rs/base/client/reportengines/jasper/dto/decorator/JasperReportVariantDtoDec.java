package net.datenwerke.rs.base.client.reportengines.jasper.dto.decorator;

import java.util.List;

import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportVariantDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;

/**
 * Dto Decorator for {@link JasperReportVariantDto}
 *
 */
public class JasperReportVariantDtoDec extends JasperReportVariantDto {

	private static final long serialVersionUID = 1L;

	public JasperReportVariantDtoDec() {
		super();
	}

	@Override
	public List<ParameterDefinitionDto> getParameterDefinitions() {
		if(null == getBaseReport())
			return null;
		return getBaseReport().getParameterDefinitions();
	}


}

package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import java.util.List;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportVariantDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;

/**
 * Dto Decorator for {@link TableReportVariantDto}
 *
 */
public class TableReportVariantDtoDec extends TableReportVariantDto implements ReportVariantDto {


	private static final long serialVersionUID = 1L;

	public TableReportVariantDtoDec() {
		super();
	}

	@Override
	public List<ParameterDefinitionDto> getParameterDefinitions() {
		if(null != getBaseReport())
			return getBaseReport().getParameterDefinitions();
		return null;
	}

}

package net.datenwerke.rs.core.client.parameters.dto.decorator;

import javax.persistence.Transient;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.interfaces.ReportAware;

/**
 * Dto Decorator for {@link ParameterDefinitionDto}
 *
 */
abstract public class ParameterDefinitionDtoDec extends ParameterDefinitionDto implements IdedDto, ReportAware {


	private static final long serialVersionUID = 1L;

	@Transient transient private ReportDto report;
	
	public ParameterDefinitionDtoDec() {
		super();
	}

	public ReportDto getReport() {
		return report;
	}
	
	public void setReport(ReportDto report) {
		this.report = report;
	}

}

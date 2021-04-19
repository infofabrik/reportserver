package net.datenwerke.rs.reportdoc.service.terminal.commands;

import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension;

@GenerateDto(dtoPackage = "net.datenwerke.rs.reportdoc.client.dto")
public class VariantTestCommandResultExtension extends CommandResultExtension {
	@ExposeToClient
	private Report report;
	
	@ExposeToClient
	private List<DatasourceDefinition> datasources;

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public List<DatasourceDefinition> getDatasources() {
		return datasources;
	}

	public void setDatasources(List<DatasourceDefinition> datasources) {
		this.datasources = datasources;
	}

}

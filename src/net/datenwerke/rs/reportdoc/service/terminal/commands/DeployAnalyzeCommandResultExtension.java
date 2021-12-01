package net.datenwerke.rs.reportdoc.service.terminal.commands;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension;

@GenerateDto(dtoPackage = "net.datenwerke.rs.reportdoc.client.dto")
public class DeployAnalyzeCommandResultExtension extends CommandResultExtension {

	@ExposeToClient
	private Report leftReport;

	@ExposeToClient
	private Report rightReport;
	
	@ExposeToClient
	private boolean ignoreCase;
	
	public void setLeftReport(Report leftReport) {
		this.leftReport = leftReport;
	}

	public Report getLeftReport() {
		return leftReport;
	}

	public void setRightReport(Report rightReport) {
		this.rightReport = rightReport;
	}

	public Report getRightReport() {
		return rightReport;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}
	
	public boolean isIgnoreCase() {
		return ignoreCase;
	}
	
}

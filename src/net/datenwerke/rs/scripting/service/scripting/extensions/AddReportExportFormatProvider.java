package net.datenwerke.rs.scripting.service.scripting.extensions;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultExtension;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.scripting.client.scripting.dto"
)
public class AddReportExportFormatProvider extends CommandResultExtension {
	
	@ExposeToClient
	private ReportDto reportType;
	
	@ExposeToClient
	private String description;
	
	@ExposeToClient
	private String title;
	
	@ExposeToClient
	private String outputFormat;

	@ExposeToClient
	private String icon;
	
	@ExposeToClient
	private boolean skipDownload = false;
	
	
	public AddReportExportFormatProvider(){
		super();
	}

	public AddReportExportFormatProvider(ReportDto reportType, String title,
			String outputFormat, String icon) {
		super();
		this.reportType = reportType;
		this.title = title;
		this.outputFormat = outputFormat;
		this.icon = icon;
	}

	public ReportDto getReportType() {
		return reportType;
	}

	public void setReportType(ReportDto reportType) {
		this.reportType = reportType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSkipDownload() {
		return skipDownload;
	}
	
	public void setSkipDownload(boolean skipDownload) {
		this.skipDownload = skipDownload;
	}
}

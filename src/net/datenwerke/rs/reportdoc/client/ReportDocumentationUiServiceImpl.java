package net.datenwerke.rs.reportdoc.client;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Random;

import net.datenwerke.gf.client.uiutils.ClientDownloadHelper;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class ReportDocumentationUiServiceImpl implements ReportDocumentationUiService {
	
	@Override
	public void openDocumentationForopen(ReportDto report) {
		int nonce = Random.nextInt();
		String url = GWT.getModuleBaseURL() + ReportDocumentationUIModule.SERVLET + "?nonce=" + nonce + "&id=" + report.getId() + "&format=PDF&download=true";
		ClientDownloadHelper.triggerDownload(url);
	}
	
	@Override
	public void openDeployAnalyzeForopen(ReportDto leftReport, ReportDto rightReport, boolean ignoreCase) {
		int nonce = Random.nextInt();
		String url = GWT.getModuleBaseURL() + ReportDocumentationUIModule.SERVLET + "?nonce=" + nonce + "&"
				+ ReportDocumentationUIModule.LEFT_REPORT_PROPERTY_ID + "=" + leftReport.getId() + "&" 
				+ ReportDocumentationUIModule.RIGHT_REPORT_PROPERTY_ID + "=" + rightReport.getId() + "&" 
				+ ReportDocumentationUIModule.IGNORE_CASE_PROPERTY_ID + "=" + ignoreCase 
				+ "&report=deployanalyze"
				+ "&format=PDF&download=true";
		ClientDownloadHelper.triggerDownload(url);
	}

	@Override
	public void openVariantTestForopen(ReportDto report, List<DatasourceDefinitionDto> datasources) {
		int nonce = Random.nextInt();
		
		String datasourceStr = datasources
			.stream()
			.map(DatasourceDefinitionDto::getName)
			.collect(Collectors.joining("&datasource=", "&datasource=", ""));
		
		if( datasources.isEmpty() ) 
			datasourceStr = "";
		
		String url = GWT.getModuleBaseURL() + ReportDocumentationUIModule.SERVLET + "?nonce=" + nonce + "&reportId="
				+ report.getId() + datasourceStr + "&report=testvariant" + "&format=PDF&download=true";

		ClientDownloadHelper.triggerDownload(url);
	}
}
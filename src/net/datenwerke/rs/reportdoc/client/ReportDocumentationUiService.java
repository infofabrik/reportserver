package net.datenwerke.rs.reportdoc.client;

import java.util.List;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

@ImplementedBy(ReportDocumentationUiServiceImpl.class)
public interface ReportDocumentationUiService {

   void openDocumentationForopen(ReportDto report);

   void openDeployAnalyzeForopen(ReportDto leftReport, ReportDto rightReport, boolean ignoreCase);

   void openVariantTestForopen(ReportDto report, List<DatasourceDefinitionDto> datasources);

}
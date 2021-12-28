package net.datenwerke.rs.core.client.helper.simpleform.config;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface SFFCExportTypeSelector extends SimpleFormFieldConfiguration {

   ReportDto getReport();

   String getExecuteReportToken();

}

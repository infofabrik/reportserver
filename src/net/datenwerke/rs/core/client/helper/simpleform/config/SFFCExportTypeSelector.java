package net.datenwerke.rs.core.client.helper.simpleform.config;

import java.util.Optional;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface SFFCExportTypeSelector extends SimpleFormFieldConfiguration {

   ReportDto getReport();

   String getExecuteReportToken();
   
   Optional<Class<? extends DatasinkDefinitionDto>> getDatasinkType();

}

package net.datenwerke.rs.core.client.reportexporter.exporter;

import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface ReportExporter {

   public interface ConfigurationFinishedCallback {
      void success();
   }

   boolean consumesConfiguration(ReportDto report);

   boolean consumes(ReportDto report);

   Request export(ReportDto report, String executorToken);

   void displayConfiguration(ReportDto report, String executorToken, boolean allowAutomaticConfig,
         ConfigurationFinishedCallback callack);

   boolean isConfigured();

   List<ReportExecutionConfigDto> getConfiguration();

   String getExportTitle();

   String getExportDescription();

   ImageResource getIcon();

   String getOutputFormat();

   boolean hasConfiguration();

   void configureFrom(List<ReportExecutionConfigDto> exportConfiguration);

   boolean wantsToBeTop(ReportDto report);

   Request prepareExportForPreview(ReportDto report, String executorToken, AsyncCallback<Void> callback);

   boolean canBeScheduled();

   boolean isSkipDownload();
   
   boolean supportsDatasink(Class<? extends DatasinkDefinitionDto> datasinkType);
   
   boolean showInExportList();
}

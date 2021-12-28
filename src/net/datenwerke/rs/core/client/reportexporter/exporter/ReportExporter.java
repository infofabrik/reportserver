package net.datenwerke.rs.core.client.reportexporter.exporter;

import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface ReportExporter {

   public interface ConfigurationFinishedCallback {
      void success();
   }

   public boolean consumesConfiguration(ReportDto report);

   public boolean consumes(ReportDto report);

   public Request export(ReportDto report, String executorToken);

   public void displayConfiguration(ReportDto report, String executorToken, boolean allowAutomaticConfig,
         ConfigurationFinishedCallback callack);

   public boolean isConfigured();

   public List<ReportExecutionConfigDto> getConfiguration();

   public String getExportTitle();

   public String getExportDescription();

   public ImageResource getIcon();

   public String getOutputFormat();

   public boolean hasConfiguration();

   public void configureFrom(List<ReportExecutionConfigDto> exportConfiguration);

   public boolean wantsToBeTop(ReportDto report);

   public Request prepareExportForPreview(ReportDto report, String executorToken, AsyncCallback<Void> callback);

   boolean canBeScheduled();

   boolean isSkipDownload();
}

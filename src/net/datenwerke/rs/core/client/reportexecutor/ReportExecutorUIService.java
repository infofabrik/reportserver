package net.datenwerke.rs.core.client.reportexecutor;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.Component;

import net.datenwerke.rs.core.client.reportexecutor.events.ExecutorEventHandler;
import net.datenwerke.rs.core.client.reportexecutor.module.ReportExecuteAreaModule;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;

public interface ReportExecutorUIService {

   public void executeReport(ReportDto report, ExecutorEventHandler eventHandler, ExecuteReportConfiguration config,
         ReportViewConfiguration... viewConfigs);

   public Component getExecuteReportComponent(ReportDto report, ExecutorEventHandler eventHandler,
         ExecuteReportConfiguration config, ReportViewConfiguration... viewConfigs);

   public PreviewViewFactory getPreviewReportComponent(ReportDto report);

   public String createExecuteReportToken(ReportDto report);

   public void executeReport(ReportDto node);

   void executeReportDirectly(ReportDto report);

   void executeReportDirectly(ReportDto report, ExecutorEventHandler eventHandler, ExecuteReportConfiguration config,
         ReportViewConfiguration... viewConfigs);

   Component getExecuteReportComponent(ReportDto report);

   Component getExecuteReportComponent(ReportDto report, boolean showVariantStorer);

   public ReportExecuteAreaModule getActiveReportExecuteAreaModule();

   public AbstractReportPreviewView getPdfPreviewView();

   public void createNewVariant(ReportDto report, TeamSpaceDto teamSpace, TsDiskFolderDto folder, String executeToken,
         String name, String description, AsyncCallback<ReportDto> callback);

   public Integer getDefaultColumnWidth();

   public Integer getMaxColumnWidth();

}

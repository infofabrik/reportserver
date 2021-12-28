package net.datenwerke.rs.scriptreport.client.scriptreport.ui;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.dto.ScriptReportDto;

public class ScriptReportPreviewViewFactory extends PreviewViewFactory {

   private final Provider<ScriptReportPreviewView> srpvProvider;

   @Inject
   public ScriptReportPreviewViewFactory(Provider<ScriptReportPreviewView> brpvProvider) {
      this.srpvProvider = brpvProvider;
   }

   @Override
   public ReportExecutorMainPanelView newInstance(ReportDto report,
         Collection<? extends ReportViewConfiguration> configs) {
      ScriptReportPreviewView view = srpvProvider.get();
      view.setReport(report);

      return view;
   }

   @Override
   public boolean consumes(ReportDto report) {
      return (report instanceof ScriptReportDto);
   }

}

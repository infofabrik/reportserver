package net.datenwerke.rs.jxlsreport.client.jxlsreport.ui;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto;

public class JxlsReportPreviewViewFactory extends PreviewViewFactory {

   private final Provider<JxlsReportPreviewView> brpvProvider;

   @Inject
   public JxlsReportPreviewViewFactory(Provider<JxlsReportPreviewView> brpvProvider) {
      this.brpvProvider = brpvProvider;
   }

   @Override
   public ReportExecutorMainPanelView newInstance(ReportDto report,
         Collection<? extends ReportViewConfiguration> configs) {
      AbstractReportPreviewView view = brpvProvider.get();
      view.setReport(report);

      return view;
   }

   @Override
   public boolean consumes(ReportDto report) {
      return (report instanceof JxlsReportDto);
   }

}

package net.datenwerke.rs.saiku.client.saiku.ui;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;

public class SaikuReportPreviewViewFactory extends PreviewViewFactory {

   private final Provider<SaikuReportPreviewView> brpvProvider;

   @Inject
   public SaikuReportPreviewViewFactory(Provider<SaikuReportPreviewView> brpvProvider) {
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
      return report instanceof SaikuReportDto;
   }

}

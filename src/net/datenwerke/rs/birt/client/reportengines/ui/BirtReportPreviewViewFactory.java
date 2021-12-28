package net.datenwerke.rs.birt.client.reportengines.ui;

import java.util.Collection;

import com.google.inject.Inject;

import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class BirtReportPreviewViewFactory extends PreviewViewFactory {

   private ReportExecutorUIService reportExecutorUIService;

   @Inject
   public BirtReportPreviewViewFactory(ReportExecutorUIService reportExecutorUIService) {
      this.reportExecutorUIService = reportExecutorUIService;
   }

   @Override
   public ReportExecutorMainPanelView newInstance(ReportDto report,
         Collection<? extends ReportViewConfiguration> configs) {
      AbstractReportPreviewView view = reportExecutorUIService.getPdfPreviewView();
      view.setReport(report);

      return view;
   }

   @Override
   public boolean consumes(ReportDto report) {
      return (report instanceof BirtReportDto);
   }

}

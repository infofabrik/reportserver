package net.datenwerke.rs.base.client.reportengines.table.ui;

import java.util.Collection;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 *
 */
public class TableReportPreviewViewFactory extends PreviewViewFactory {

   /**
    * The provider used to supply the actual instances
    */
   private final Provider<TableReportPreviewView> jroProvider;

   @Inject
   public TableReportPreviewViewFactory(
         Provider<TableReportPreviewView> jroProvider
         ) {
      this.jroProvider = jroProvider;
   }

   /**
    * {@inheritDoc}
    */
   public ReportExecutorMainPanelView newInstance(ReportDto report,
         Collection<? extends ReportViewConfiguration> configs) {
      TableReportPreviewView view = jroProvider.get();
      view.setReport(report);

      return view;
   }

   /**
    * {@inheritDoc}
    */
   public boolean consumes(ReportDto report) {
      return (report instanceof TableReportDto) && !((TableReportDto) report).isCubeFlag();
   }

}

package net.datenwerke.rs.base.client.reportengines.table.cubeconfig;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.security.client.security.dto.ExecuteDto;

public class CubeConfigViewFactory implements ReportViewFactory {

   private final Provider<CubeConfigView> cubeConfigViewProvider;

   @Inject
   public CubeConfigViewFactory(Provider<CubeConfigView> cubeConfigViewProvider) {
      this.cubeConfigViewProvider = cubeConfigViewProvider;
   }

   public CubeConfigView newInstance(ReportDto report, Collection<? extends ReportViewConfiguration> configs) {
      CubeConfigView fw = cubeConfigViewProvider.get();
      fw.setReport((TableReportDto) report);
      return fw;
   }

   public boolean consumes(ReportDto report) {
      return report instanceof TableReportDto && report.hasAccessRight(ExecuteDto.class)
            && ((TableReportDto) report).isCubeFlag() && !report.isConfigurationProtected();
   }

   @Override
   public String getViewId() {
      return CubeConfigView.VIEW_ID;
   }
}

package net.datenwerke.rs.core.client.parameters.propertywidgets;

import java.util.Collection;

import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class ParameterViewFactory implements ReportViewFactory {

   public ReportExecutorMainPanelView newInstance(ReportDto report,
         Collection<? extends ReportViewConfiguration> configs) {
      return new ParameterView(report);
   }

   public boolean consumes(ReportDto report) {
      if (null != report.getParameterDefinitions() && report.getParameterDefinitions().size() > 0
            && !report.isConfigurationProtected()) {
         for (ParameterDefinitionDto def : report.getParameterDefinitions())
            if (!def.isHidden())
               return true;
      }
      return false;
   }

   @Override
   public String getViewId() {
      return ParameterView.VIEW_ID;
   }
}

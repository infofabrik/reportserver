package net.datenwerke.rs.core.client.reportexecutor.ui;

import java.util.Collection;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface ReportViewFactory {

   public ReportExecutorMainPanelView newInstance(ReportDto report,
         Collection<? extends ReportViewConfiguration> configs);

   public boolean consumes(ReportDto report);

   public String getViewId();
}

package net.datenwerke.rs.core.client.reportexecutor;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportExecutorViewToolbarHook;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class ReportRefreshExecutionHooker implements ReportExecutorViewToolbarHook {

   private final ToolbarService toolbarService;

   @Inject
   public ReportRefreshExecutionHooker(ToolbarService toolbarService) {

      this.toolbarService = toolbarService;
   }

   @Override
   public boolean reportPreviewViewToolbarHook_addLeft(ToolBar toolbar, final ReportDto report,
         ReportExecutorInformation info, ReportExecutorMainPanel mainPanel) {
      return false;
   }

   @Override
   public boolean reportPreviewViewToolbarHook_addRight(ToolBar toolbar, final ReportDto report,
         ReportExecutorInformation info, ReportExecutorMainPanel mainPanel) {
      DwTextButton refreshBtn = toolbarService.createSmallButtonLeft(BaseMessages.INSTANCE.previewRefresh(),
            BaseIcon.REFRESH);
      refreshBtn.addSelectHandler(event -> {
         for (ReportExecutorMainPanelView view : mainPanel.getViews()) {
            if (mainPanel.isViewSelected(view) && view instanceof AbstractReportPreviewView) {
               ((AbstractReportPreviewView) view).reload();
            }
         }
      });
      toolbar.add(refreshBtn);

      return true;
   }

   @Override
   public void reportPreviewViewToolbarHook_reportUpdated(ReportDto report, ReportExecutorInformation info) {

   }

}

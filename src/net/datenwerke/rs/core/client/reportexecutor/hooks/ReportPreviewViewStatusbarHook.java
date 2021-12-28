package net.datenwerke.rs.core.client.reportexecutor.hooks;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface ReportPreviewViewStatusbarHook extends Hook {

   public void reportPreviewViewStatusbarHook_addLeft(AbstractReportPreviewView reportPreviewView, ToolBar toolbar,
         ReportDto report);

   public void reportPreviewViewStatusbarHook_addRight(AbstractReportPreviewView reportPreviewView, ToolBar toolbar,
         ReportDto report);

   public void reportPreviewViewStatusbarHook_reportUpdated(ReportDto report,
         AbstractReportPreviewView abstractReportPreviewView, boolean configChanged);

   public void reportPreviewViewStatusbarHook_reportToBeReloaded(ReportDto report,
         AbstractReportPreviewView abstractReportPreviewView);

   public void reportPreviewViewStatusbarHook_reportLoaded(ReportDto report, AsyncCallback<DwModel> AsyncCallback,
         DwModel result, boolean configChanged);

   public void reportPreviewViewStatusbarHook_cancel(ReportDto report,
         AbstractReportPreviewView abstractReportPreviewView);
}

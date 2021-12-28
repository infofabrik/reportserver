package net.datenwerke.rs.core.client.reportexporter.hookers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportExecutorViewToolbarHook;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter.ConfigurationFinishedCallback;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * 
 *
 */
public class ReportViewExportButtonHooker implements ReportExecutorViewToolbarHook {

   private final ReportExporterUIService reportExporterService;

   private Map<ReportExporter, Component> exporterMap = new HashMap<ReportExporter, Component>();

   private List<ReportExporter> exporters;

   @Inject
   public ReportViewExportButtonHooker(ReportExporterUIService reportExporterService) {

      /* store objects */
      this.reportExporterService = reportExporterService;
   }

   @Override
   public boolean reportPreviewViewToolbarHook_addLeft(ToolBar toolbar, final ReportDto report,
         final ReportExecutorInformation info, final ReportExecutorMainPanel mainPanel) {
      exporters = reportExporterService.getCleanedUpAvailableExporters(report);
      if (exporters.isEmpty())
         return false;

      Iterator<ReportExporter> exporterIt = exporters.iterator();

      /* create first large button */
      final ReportExporter first = exporterIt.next();
      TextButton exportBtn = null;
      if (exporterIt.hasNext())
         exportBtn = new DwSplitButton(ReportExporterMessages.INSTANCE.exportReportTo(first.getExportTitle()));
      else
         exportBtn = new DwTextButton(ReportExporterMessages.INSTANCE.exportReportTo(first.getExportTitle()));

      exportBtn.setArrowAlign(ButtonArrowAlign.RIGHT);
      if (null != first.getIcon())
         exportBtn.setIcon(first.getIcon());
      exportBtn.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            first.displayConfiguration(report, info.getExecuteReportToken(), true, new ConfigurationFinishedCallback() {
               @Override
               public void success() {
                  List<ReportExecutorMainPanelView> views = mainPanel.getViews();
                  if (!validateViews(views))
                     return;

                  first.export(report, info.getExecuteReportToken());
               }
            });
         }
      });

      toolbar.add(exportBtn);

      /* others */
      if (!exporterIt.hasNext())
         return true;

      Menu exportMenu = new DwMenu();
      exportBtn.setMenu(exportMenu);
      toolbar.add(exportBtn);

      while (exporterIt.hasNext()) {
         ReportExporter exporter = exporterIt.next();
         MenuItem item = new DwMenuItem(exporter.getExportTitle(), exporter.getIcon());
         exporterMap.put(exporter, item);
         initButton(item, exporter, report, info, mainPanel);
         exportMenu.add(item);
      }

      return false;
   }

   private boolean validateViews(final List<ReportExecutorMainPanelView> views) {
      for (ReportExecutorMainPanelView view : views) {
         List<String> errorMsgs = view.validateView();
         if (null != errorMsgs && !errorMsgs.isEmpty()) {
            String errorMsg = "";
            boolean first = true;
            for (String msg : errorMsgs) {
               if (first)
                  first = false;
               else
                  errorMsg += "<br/>";
               errorMsg += msg;
            }

            new DwAlertMessageBox(BaseMessages.INSTANCE.error(), errorMsg).show();
            return false;
         }
      }

      return true;
   }

   private void initButton(MenuItem btn, final ReportExporter exporter, final ReportDto report,
         final ReportExecutorInformation info, final ReportExecutorMainPanel mainPanel) {
      btn.addSelectionHandler(new SelectionHandler<Item>() {

         @Override
         public void onSelection(SelectionEvent<Item> event) {
            exporter.displayConfiguration(report, info.getExecuteReportToken(), true,
                  new ConfigurationFinishedCallback() {
                     @Override
                     public void success() {
                        List<ReportExecutorMainPanelView> views = mainPanel.getViews();
                        if (!validateViews(views))
                           return;

                        exporter.export(report, info.getExecuteReportToken());
                     }
                  });
         }
      });
   }

   @Override
   public boolean reportPreviewViewToolbarHook_addRight(ToolBar toolbar, ReportDto report,
         ReportExecutorInformation info, ReportExecutorMainPanel mainPanel) {
      return false;
   }

   @Override
   public void reportPreviewViewToolbarHook_reportUpdated(ReportDto report, ReportExecutorInformation info) {
      for (ReportExporter exporter : exporters) {
         if (!exporter.consumesConfiguration(report))
            exporterMap.get(exporter).disable();
         else
            exporterMap.get(exporter).enable();
      }
   }

}

package net.datenwerke.rs.saiku.client.saiku.hookers;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStringNoHtmlDecode;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCTextArea;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportExecutorViewToolbarHook;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.eximport.client.eximport.locale.ExImportMessages;
import net.datenwerke.rs.saiku.client.saiku.locale.SaikuMessages;
import net.datenwerke.rs.saikupivot.client.SaikuPivotDao;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class CubeExportHooker implements ReportExecutorViewToolbarHook {

   private final ToolbarService toolbarService;
   private final EnterpriseUiService enterpriseService;
   private final ReportExecutorUIService reportExecutorService;
   private final SaikuPivotDao saikuPivotDao;

   @Inject
   public CubeExportHooker(
         ToolbarService toolbarService, 
         EnterpriseUiService enterpriseService,
         ReportExecutorUIService reportExecutorService, 
         SaikuPivotDao saikuPivotDao
         ) {
      super();
      this.toolbarService = toolbarService;
      this.enterpriseService = enterpriseService;
      this.reportExecutorService = reportExecutorService;
      this.saikuPivotDao = saikuPivotDao;
   }

   @Override
   public boolean reportPreviewViewToolbarHook_addLeft(ToolBar toolbar, ReportDto report,
         ReportExecutorInformation info, final ReportExecutorMainPanel mainPanel) {
      if (!(report instanceof TableReportDto))
         return false;

      final TableReportDto tableReport = (TableReportDto) report;
      if (!enterpriseService.isEnterprise() || !tableReport.isCubeFlag())
         return false;

      DwTextButton exportBtn = toolbarService.createSmallButtonLeft(SaikuMessages.INSTANCE.exportCube(),
            BaseIcon.EXPORT);
      exportBtn.setArrowAlign(ButtonArrowAlign.RIGHT);
      toolbar.add(exportBtn);

      final Menu menu = new Menu();

      exportBtn.setMenu(menu);

      MenuItem mondrianItem = new DwMenuItem("Mondrian");
      menu.add(mondrianItem);
      mondrianItem.addSelectionHandler(new SelectionHandler<Item>() {

         @Override
         public void onSelection(SelectionEvent<Item> event) {
            startProgress();
            saikuPivotDao.cubeExport(reportExecutorService.createExecuteReportToken(tableReport), tableReport,
                  new RsAsyncCallback<String>() {
                     @Override
                     public void onSuccess(String result) {
                        displayQuickExportResult(result);
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                        new DetailErrorDialog(caught).show();
                     }
                  });
         }
      });

      return false;
   }

   protected void startProgress() {
      try {
         InfoConfig infoConfig = new DefaultInfoConfig(ExImportMessages.INSTANCE.quickExportProgressTitle(),
               ExImportMessages.INSTANCE.exportWait());
         infoConfig.setWidth(350);
         infoConfig.setDisplay(3500);
         Info.display(infoConfig);
      } catch (Exception e) {
      }

   }

   protected void displayQuickExportResult(String result) {
      final DwWindow window = new DwWindow();
      window.setSize(640, 480);

      SimpleForm form = SimpleForm.getInlineLabelessInstance();
      window.add(form);

      String key = form.addField(String.class, new SFFCTextArea() {

         @Override
         public int getWidth() {
            return 580;
         }

         @Override
         public int getHeight() {
            return 400;
         }
      }, new SFFCStringNoHtmlDecode() {
      });
      form.setValue(key, result);

      form.loadFields();

      DwTextButton closeBtn = new DwTextButton(BaseMessages.INSTANCE.close());
      closeBtn.addSelectHandler(event -> window.hide());
      window.addButton(closeBtn);

      window.show();
   }

   @Override
   public boolean reportPreviewViewToolbarHook_addRight(ToolBar toolbar, ReportDto report,
         ReportExecutorInformation info, ReportExecutorMainPanel mainPanel) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void reportPreviewViewToolbarHook_reportUpdated(ReportDto report, ReportExecutorInformation info) {
      // TODO Auto-generated method stub

   }

}

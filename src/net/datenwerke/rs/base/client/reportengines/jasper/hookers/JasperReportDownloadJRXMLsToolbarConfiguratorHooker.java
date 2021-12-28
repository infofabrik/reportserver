package net.datenwerke.rs.base.client.reportengines.jasper.hookers;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.fileselection.locale.FileSelectionMessages;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.FormView;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gf.client.uiutils.ClientDownloadHelper;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.client.reportengines.jasper.locale.JasperMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class JasperReportDownloadJRXMLsToolbarConfiguratorHooker implements MainPanelViewToolbarConfiguratorHook {

   private final ToolbarService toolbarUtils;
   private final UtilsUIService utilsUIService;

   @Inject
   public JasperReportDownloadJRXMLsToolbarConfiguratorHooker(ToolbarService toolbarUtils,
         UtilsUIService utilsUIService) {

      /* store objects */
      this.toolbarUtils = toolbarUtils;
      this.utilsUIService = utilsUIService;
   }

   public void mainPanelViewToolbarConfiguratorHook_addLeft(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
      if (!(selectedNode instanceof JasperReportDto))
         return;
      if (!(view instanceof FormView))
         return;

      final JasperReportDto report = (JasperReportDto) selectedNode;

      /* add parameter */
      DwTextButton createPreviewBtn = toolbarUtils
            .createSmallButtonLeft(JasperMessages.INSTANCE.jasperDownloadToolbarButtonText(), BaseIcon.FILE_PICTURE_O);
      createPreviewBtn.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            if (null == report.getMasterFile()) {
               new DwAlertMessageBox(BaseMessages.INSTANCE.warning(), FileSelectionMessages.INSTANCE.noFileUploaded())
                     .show();
               return;
            }

            String id = String.valueOf(report.getId());
            String url = GWT.getModuleBaseURL() + "JRXMLDownload?id=" + id; //$NON-NLS-1$
            ClientDownloadHelper.triggerDownload(url);
         }
      });

      toolbar.add(createPreviewBtn);
   }

   public void mainPanelViewToolbarConfiguratorHook_addRight(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {

   }

}

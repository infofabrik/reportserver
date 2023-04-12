package net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.ex.hookers;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.ex.RemoteServerManagerExportDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.client.eximport.ex.QuickExportHookerBase;

public class ExportButtonHook extends QuickExportHookerBase {

   private final RemoteServerManagerExportDao exportDao;

   @Inject
   public ExportButtonHook(
         RemoteServerManagerExportDao exportDao, 
         ToolbarService toolbarUtils, 
         UtilsUIService utilsUiService
         ) {
      super(toolbarUtils, utilsUiService);

      /* store objects */
      this.exportDao = exportDao;
   }

   @Override
   protected void quickExportClicked(AbstractNodeDto selectedNode) {
      startProgress();
      exportDao.quickExport((AbstractRemoteServerManagerNodeDto) selectedNode, getExportCallback());

   }

   @Override
   protected void loadAndDisplayResult(AsyncCallback<String> loadAndDisplayResultCallback) {
      exportDao.loadResult(loadAndDisplayResultCallback);

   }

   @Override
   protected boolean viewApplies(MainPanelView view, AbstractNodeDto selectedNode) {
      if (!(selectedNode instanceof AbstractRemoteServerManagerNodeDto))
         return false;
      if (!MainPanelView.ID_MAIN_PROPERTIES_VIEW.equals(view.getViewId()))
         return false;

      return true;
   }

}
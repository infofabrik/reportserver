package net.datenwerke.rs.transport.client.transport.eximport.ex.hookers;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.transport.client.transport.dto.AbstractTransportManagerNodeDto;
import net.datenwerke.rs.transport.client.transport.eximport.ex.TransportManagerExportDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.client.eximport.ex.QuickExportHookerBase;

public class ExportButtonHook extends QuickExportHookerBase {

   private final TransportManagerExportDao exportDao;

   @Inject
   public ExportButtonHook(
         TransportManagerExportDao exportDao, 
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
      exportDao.quickExport((AbstractTransportManagerNodeDto) selectedNode, getExportCallback());

   }

   @Override
   protected void loadAndDisplayResult(AsyncCallback<String> loadAndDisplayResultCallback) {
      exportDao.loadResult(loadAndDisplayResultCallback);

   }

   @Override
   protected boolean viewApplies(MainPanelView view, AbstractNodeDto selectedNode) {
      if (!(selectedNode instanceof AbstractTransportManagerNodeDto))
         return false;
      if (!MainPanelView.ID_MAIN_PROPERTIES_VIEW.equals(view.getViewId()))
         return false;

      return true;
   }

}
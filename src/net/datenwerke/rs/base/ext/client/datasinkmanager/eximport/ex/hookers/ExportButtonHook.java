package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.ex.hookers;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.ex.DatasinkManagerExportDao;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.client.eximport.ex.QuickExportHookerBase;

public class ExportButtonHook extends QuickExportHookerBase {

   private final DatasinkManagerExportDao dsDao;

   @Inject
   public ExportButtonHook(DatasinkManagerExportDao dsDao, ToolbarService toolbarUtils, UtilsUIService utilsUiService) {
      super(toolbarUtils, utilsUiService);

      /* store objects */
      this.dsDao = dsDao;
   }

   @Override
   protected void quickExportClicked(AbstractNodeDto selectedNode) {
      startProgress();
      dsDao.quickExport((AbstractDatasinkManagerNodeDto) selectedNode, getExportCallback());

   }

   @Override
   protected void loadAndDisplayResult(AsyncCallback<String> loadAndDisplayResultCallback) {
      dsDao.loadResult(loadAndDisplayResultCallback);

   }

   @Override
   protected boolean viewApplies(MainPanelView view, AbstractNodeDto selectedNode) {
      if (!(selectedNode instanceof AbstractDatasinkManagerNodeDto))
         return false;
      if (!MainPanelView.ID_MAIN_PROPERTIES_VIEW.equals(view.getViewId()))
         return false;

      return true;
   }

}
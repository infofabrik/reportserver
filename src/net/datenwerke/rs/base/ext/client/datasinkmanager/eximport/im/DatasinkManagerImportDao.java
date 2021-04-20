package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.rpc.DatasinkManagerImportRpcServiceAsync;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public class DatasinkManagerImportDao extends Dao {
   private final DatasinkManagerImportRpcServiceAsync rpcService;

   @Inject
   public DatasinkManagerImportDao(DatasinkManagerImportRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void loadTree(AsyncCallback<List<ImportTreeModel>> callback) {
      rpcService.loadTree(transformAndKeepCallback(callback));
   }

}
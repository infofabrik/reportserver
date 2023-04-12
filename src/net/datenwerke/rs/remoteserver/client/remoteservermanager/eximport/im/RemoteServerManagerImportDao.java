package net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.rpc.RemoteServerManagerImportRpcServiceAsync;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public class RemoteServerManagerImportDao extends Dao {
   private final RemoteServerManagerImportRpcServiceAsync rpcService;

   @Inject
   public RemoteServerManagerImportDao(RemoteServerManagerImportRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void loadTree(AsyncCallback<List<ImportTreeModel>> callback) {
      rpcService.loadTree(transformAndKeepCallback(callback));
   }

}
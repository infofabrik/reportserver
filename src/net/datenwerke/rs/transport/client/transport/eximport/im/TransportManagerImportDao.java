package net.datenwerke.rs.transport.client.transport.eximport.im;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.transport.client.transport.eximport.im.rpc.TransportManagerImportRpcServiceAsync;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public class TransportManagerImportDao extends Dao {
   private final TransportManagerImportRpcServiceAsync rpcService;

   @Inject
   public TransportManagerImportDao(TransportManagerImportRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void loadTree(AsyncCallback<List<ImportTreeModel>> callback) {
      rpcService.loadTree(transformAndKeepCallback(callback));
   }

}
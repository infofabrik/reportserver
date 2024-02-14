package net.datenwerke.rs.transport.client.transport;

import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.transport.client.transport.dto.TransportCheckEntryDto;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.client.transport.rpc.TransportRpcServiceAsync;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public class TransportDao extends Dao {
   
   private final TransportRpcServiceAsync rpcService;
   
   @Inject
   public TransportDao(TransportRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }
   
   public void addElement(TransportDto transportDto, AbstractNodeDto elementDto, boolean includeVariants,
         AsyncCallback<Void> callback) {
      rpcService.addElement(transportDto, elementDto, includeVariants, transformAndKeepCallback(callback));
   }

   public void rpull(AsyncCallback<Void> callback) {
      rpcService.rpull(transformAndKeepCallback(callback));
   }
   
   public Request apply(TransportDto transportDto, AsyncCallback<Void> callback) {
      return rpcService.apply(transportDto, transformAndKeepCallback(callback));
   }

   public void loadImportedTransports(RsAsyncCallback<List<TransportDto>> callback) {
      rpcService.loadImportedTransports(transformListCallback(callback));
   }

   public void removeElements(TransportDto transportDto, List<ImportTreeModel> elementsToRemove,
         AsyncCallback<Void> callback) {
      rpcService.removeElements(transportDto, elementsToRemove, transformAndKeepCallback(callback));
   }

   public void addAllDescendants(TransportDto transportDto, AbstractNodeDto elementDto, boolean includeVariants,
         AsyncCallback<Void> callback) {
      rpcService.addAllDescendants(transportDto, elementDto, includeVariants, transformAndKeepCallback(callback));
   }

   public Request checkPreconditions(TransportDto transportDto, AsyncCallback<List<TransportCheckEntryDto>> callback) {
      return rpcService.checkPreconditions(transportDto, transformAndKeepCallback(callback));
   }

   public void extractTreeModel(TransportDto transportDto, AsyncCallback<List<ImportTreeModel>> callback) {
      rpcService.extractTreeModel(transportDto, transformAndKeepCallback(callback));
   }

}

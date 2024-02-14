package net.datenwerke.rs.transport.client.transport.rpc;

import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.transport.client.transport.dto.TransportCheckEntryDto;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public interface TransportRpcServiceAsync {

   void addElement(TransportDto transportDto, AbstractNodeDto elementDto, boolean includeVariants,
         AsyncCallback<Void> callback);

   void rpull(AsyncCallback<Void> callback);
   
   Request apply(TransportDto transportDto, AsyncCallback<Void> callback);

   void loadImportedTransports(AsyncCallback<List<TransportDto>> callback);

   void removeElements(TransportDto transportDto, List<ImportTreeModel> elementsToRemove, AsyncCallback<Void> callback);

   void addAllDescendants(TransportDto transportDto, AbstractNodeDto elementDto, boolean includeVariants,
         AsyncCallback<Void> callback);
   
   Request checkPreconditions(TransportDto transportDto, AsyncCallback<List<TransportCheckEntryDto>> callback);
   
   void extractTreeModel(TransportDto transportDto, AsyncCallback<List<ImportTreeModel>> callback);
}

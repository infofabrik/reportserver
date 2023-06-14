package net.datenwerke.rs.transport.client.transport.eximport.im.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public interface TransportManagerImportRpcServiceAsync {

   void loadTree(AsyncCallback<List<ImportTreeModel>> callback);
}
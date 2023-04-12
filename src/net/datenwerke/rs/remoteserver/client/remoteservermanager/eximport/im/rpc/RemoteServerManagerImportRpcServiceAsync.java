package net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public interface RemoteServerManagerImportRpcServiceAsync {

   void loadTree(AsyncCallback<List<ImportTreeModel>> callback);
}
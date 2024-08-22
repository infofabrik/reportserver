package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public interface DatasinkManagerImportRpcServiceAsync {

   void loadTree(AsyncCallback<List<ImportTreeModel>> callback);
}
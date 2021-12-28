package net.datenwerke.usermanager.ext.client.eximport.im.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public interface UserManagerImportRpcServiceAsync {

	void loadTree(AsyncCallback<List<ImportTreeModel>> callback);

}

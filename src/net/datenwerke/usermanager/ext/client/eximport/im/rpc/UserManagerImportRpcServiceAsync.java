package net.datenwerke.usermanager.ext.client.eximport.im.rpc;

import java.util.List;

import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserManagerImportRpcServiceAsync {

	void loadTree(AsyncCallback<List<ImportTreeModel>> callback);

}

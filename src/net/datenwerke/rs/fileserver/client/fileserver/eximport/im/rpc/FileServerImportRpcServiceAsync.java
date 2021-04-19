package net.datenwerke.rs.fileserver.client.fileserver.eximport.im.rpc;

import java.util.List;

import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FileServerImportRpcServiceAsync {

	void loadTree(AsyncCallback<List<ImportTreeModel>> callback);

}

package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im.rpc;

import java.util.List;

import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DatasourceManagerImportRpcServiceAsync {

	void loadTree(AsyncCallback<List<ImportTreeModel>> callback);

}

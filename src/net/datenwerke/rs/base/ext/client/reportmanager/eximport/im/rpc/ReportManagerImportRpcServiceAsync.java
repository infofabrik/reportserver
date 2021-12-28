package net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

public interface ReportManagerImportRpcServiceAsync {

	void loadTree(AsyncCallback<List<ImportTreeModel>> callback);

}

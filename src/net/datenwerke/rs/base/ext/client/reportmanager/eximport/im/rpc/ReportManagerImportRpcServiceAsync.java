package net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.rpc;

import java.util.List;

import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReportManagerImportRpcServiceAsync {

	void loadTree(AsyncCallback<List<ImportTreeModel>> callback);

}

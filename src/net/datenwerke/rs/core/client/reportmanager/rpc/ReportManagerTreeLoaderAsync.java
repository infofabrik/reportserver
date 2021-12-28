package net.datenwerke.rs.core.client.reportmanager.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.treedb.client.treedb.rpc.RPCTreeLoaderAsync;

public interface ReportManagerTreeLoaderAsync extends RPCTreeLoaderAsync {

   void getReportsInCatalog(ReportFolderDto folder, boolean showVariants, AsyncCallback<String[][]> callback);

}

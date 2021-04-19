package net.datenwerke.rs.core.client.reportmanager.rpc;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.treedb.client.treedb.rpc.RPCTreeLoader;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reportmanager_tree")
public interface ReportManagerTreeLoader extends RemoteService, RPCTreeLoader {

	public String[][] getReportsInCatalog(ReportFolderDto folder, boolean showVariants) throws ServerCallFailedException;
}

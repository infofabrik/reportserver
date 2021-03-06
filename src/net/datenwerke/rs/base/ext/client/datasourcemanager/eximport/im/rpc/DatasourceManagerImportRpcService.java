package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

@RemoteServiceRelativePath("datasources_import")
public interface DatasourceManagerImportRpcService extends RemoteService {

   public List<ImportTreeModel> loadTree() throws ServerCallFailedException;

}

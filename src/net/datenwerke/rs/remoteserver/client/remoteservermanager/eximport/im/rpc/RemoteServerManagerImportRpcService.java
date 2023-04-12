package net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

@RemoteServiceRelativePath("remoteservers_import")
public interface RemoteServerManagerImportRpcService extends RemoteService {

   public List<ImportTreeModel> loadTree() throws ServerCallFailedException;

}
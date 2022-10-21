package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

@RemoteServiceRelativePath("datasinks_import")
public interface DatasinkManagerImportRpcService extends RemoteService {

   public List<ImportTreeModel> loadTree() throws ServerCallFailedException;

}
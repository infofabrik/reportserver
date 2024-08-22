package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;

@RemoteServiceRelativePath("datasinkmanager_export")
public interface DatasinkManagerExportRpcService extends RemoteService {
   public void quickExport(AbstractDatasinkManagerNodeDto dto) throws ServerCallFailedException;

   public String loadResult() throws ServerCallFailedException;
}
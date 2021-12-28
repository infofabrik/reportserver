package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;

@RemoteServiceRelativePath("datasourcemanager_export")
public interface DatasourceManagerExportRpcService extends RemoteService {

   public void quickExport(AbstractDatasourceManagerNodeDto dto) throws ServerCallFailedException;

   public String loadResult() throws ServerCallFailedException;
}

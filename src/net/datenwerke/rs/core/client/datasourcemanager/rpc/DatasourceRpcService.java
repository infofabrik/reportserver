package net.datenwerke.rs.core.client.datasourcemanager.rpc;

import java.util.Map;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.client.datasources.DatasourceInfoType;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;

@RemoteServiceRelativePath("datasources")
public interface DatasourceRpcService extends RemoteService {
   public DatasourceDefinitionDto getDefaultDatasource() throws ServerCallFailedException;

   Map<DatasourceInfoType, SafeHtml> getDatasourceInfoDetailsAsHtml(DatabaseDatasourceDto datasourceDto)
         throws ServerCallFailedException;

}

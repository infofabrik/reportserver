package net.datenwerke.rs.dsbundle.client.dsbundle.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto;

@RemoteServiceRelativePath("datasourcebundle")
public interface DatasourceBundleRpcService extends RemoteService {

   List<String> getAvailableBundleKeys() throws ServerCallFailedException;

   Map<String, String> getBundleSelectorConfiguration() throws ServerCallFailedException;

   void setSelectedBundle(String bundleKey) throws ServerCallFailedException;

   boolean testConnection(DatabaseBundleDto databaseBundleDto) throws ServerCallFailedException;

}

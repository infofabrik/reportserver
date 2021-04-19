package net.datenwerke.rs.dsbundle.client.dsbundle.rpc;

import java.util.List;
import java.util.Map;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("datasourcebundle")
public interface DatasourceBundleRpcService extends RemoteService {

	public List<String> getAvailableBundleKeys() throws ServerCallFailedException;
	
	public Map<String, String> getBundleSelectorConfiguration() throws ServerCallFailedException;

	public void setSelectedBundle(String bundleKey) throws ServerCallFailedException;
	
}

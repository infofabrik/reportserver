package net.datenwerke.rs.core.client.urlview.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

@RemoteServiceRelativePath("urlviewconfig")
public interface UrlViewRpcService extends RemoteService {

   Map<String, Map<String, List<String[]>>> loadViewConfiguration() throws ServerCallFailedException;
}

package net.datenwerke.gf.client.config.rpc;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

@RemoteServiceRelativePath("clientconfig")
public interface ClientConfigRpcService extends RemoteService {

   public String getConfigFile(String configfile) throws ServerCallFailedException;

   HashMap<String, String> getConfigProperties(String identifier);
}

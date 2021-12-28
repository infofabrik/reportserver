package net.datenwerke.rs.core.server.urlview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.urlview.rpc.UrlViewRpcService;
import net.datenwerke.rs.core.service.urlview.annotations.UrlViewConfig;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;

@Singleton
public class UrlViewRpcServiceImpl extends SecuredRemoteServiceServlet implements UrlViewRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3819431223215867342L;
	
	private final Provider<Map<String,Map<String, List<String[]>>>> configProvider;

	@Inject
	public UrlViewRpcServiceImpl(
		@UrlViewConfig Provider<Map<String,Map<String, List<String[]>>>> configProvider
		){
			this.configProvider = configProvider;
		
	}

	@Override
	public Map<String,Map<String, List<String[]>>> loadViewConfiguration() throws ServerCallFailedException {
		try{
			return configProvider.get();
		}catch(IllegalArgumentException e){
			return new HashMap<String, Map<String,List<String[]>>>();
		}
	}
}

package net.datenwerke.rs.enterprise.client;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.enterprise.client.rpc.EnterpriseCheckRpcServiceAsync;

public class EnterpriseCheckDao extends Dao {

	private final EnterpriseCheckRpcServiceAsync rpcService;

	@Inject
	public EnterpriseCheckDao(EnterpriseCheckRpcServiceAsync rpcService) {
		super();
		this.rpcService = rpcService;
	}
	
	public void isEnterprise(RsAsyncCallback<EnterpriseInformationDto> callback){
		rpcService.getEnterpriseInfos(transformAndKeepCallback(callback));
	}
}

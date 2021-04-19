package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.rpc.GeneralInfoRpcServiceAsync;

public class GeneralInfoDao extends Dao {

	private final GeneralInfoRpcServiceAsync rpcService;

	@Inject
	public GeneralInfoDao(
			GeneralInfoRpcServiceAsync rpcService) {
			this.rpcService = rpcService;
	}
	
	public void loadGeneralInfo(RsAsyncCallback<GeneralInfoDto> callback){
		rpcService.loadGeneralInfo(transformAndKeepCallback(callback));
	}

}

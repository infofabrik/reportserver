package net.datenwerke.rs.globalconstants.client.globalconstants;

import java.util.Collection;
import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.DaoAsyncCallback;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto;
import net.datenwerke.rs.globalconstants.client.globalconstants.rpc.GlobalConstantsRpcServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * 
 *
 */
public class GlobalConstantsDao extends Dao {

	private final GlobalConstantsRpcServiceAsync rpcService;
	
	@Inject
	public GlobalConstantsDao(
		GlobalConstantsRpcServiceAsync rpcService
		){
		this.rpcService = rpcService;
	}
	
	public void loadGlobalConstants(AsyncCallback<List<GlobalConstantDto>> callback){
		rpcService.loadGlobalConstants(transformListCallback(callback));
	}

	public void addNewConstant(
			RsAsyncCallback<GlobalConstantDto> callback) {
		rpcService.addNewConstant(transformDtoCallback(callback));
	}
	
	public void updateConstant(GlobalConstantDto constantDto,
			AsyncCallback<GlobalConstantDto> callback){
		rpcService.updateConstant(constantDto, transformDtoCallback(callback));
	}

	public void removeAllConstants(AsyncCallback<Void> callback, Collection<GlobalConstantDto> oldConstants){
		DaoAsyncCallback<Void> tCallback = transformAndKeepCallback(callback);
		tCallback.setDtosToDetach((Collection)oldConstants);
		rpcService.removeAllConstants(tCallback);
	}
	
	public void removeConstants(Collection<GlobalConstantDto> constants,
			AsyncCallback<Void> callback){
		DaoAsyncCallback<Void> tCallback = transformAndKeepCallback(callback);
		tCallback.setDtosToDetach((Collection)constants);
		rpcService.removeConstants(constants, tCallback);
	}
}

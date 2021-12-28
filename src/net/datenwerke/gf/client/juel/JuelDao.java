package net.datenwerke.gf.client.juel;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.juel.dto.JuelResultDto;
import net.datenwerke.gf.client.juel.rpc.JuelRpcServiceAsync;
import net.datenwerke.gxtdto.client.dtomanager.Dao;

public class JuelDao extends Dao {


	private final JuelRpcServiceAsync rpcService;

	@Inject
	public JuelDao(JuelRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	
	public void evaluateExpression(String expression, AsyncCallback<JuelResultDto> callback){
		rpcService.evaluateExpression(expression, callback);
	}

}

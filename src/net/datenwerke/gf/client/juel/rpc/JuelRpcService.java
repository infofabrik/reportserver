package net.datenwerke.gf.client.juel.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gf.client.juel.dto.JuelResultDto;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

@RemoteServiceRelativePath("juel")
public interface JuelRpcService extends RemoteService {

	JuelResultDto evaluateExpression(String expression) throws ServerCallFailedException;
	
	 
}

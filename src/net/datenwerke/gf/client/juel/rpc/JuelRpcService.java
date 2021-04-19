package net.datenwerke.gf.client.juel.rpc;

import net.datenwerke.gf.client.juel.dto.JuelResultDto;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("juel")
public interface JuelRpcService extends RemoteService {

	JuelResultDto evaluateExpression(String expression) throws ServerCallFailedException;
	
	 
}

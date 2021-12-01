package net.datenwerke.rs.authenticator.cr.client;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.authenticator.client.login.dto.ChallengeResponseContainer;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("security_challengeresponse")
public interface ChallengeResponseRpcService extends RemoteService {
	
	public ChallengeResponseContainer requestChallenge() throws ServerCallFailedException;
	
	public String getHmacPassphrase();
}

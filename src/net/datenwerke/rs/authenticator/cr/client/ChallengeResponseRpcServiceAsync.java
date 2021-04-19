package net.datenwerke.rs.authenticator.cr.client;

import net.datenwerke.rs.authenticator.client.login.dto.ChallengeResponseContainer;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChallengeResponseRpcServiceAsync {

	void requestChallenge(AsyncCallback<ChallengeResponseContainer> callback);

	void getHmacPassphrase(AsyncCallback<String> callback);

}

package net.datenwerke.rs.authenticator.cr.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.authenticator.client.login.dto.ChallengeResponseContainer;

public interface ChallengeResponseRpcServiceAsync {

	void requestChallenge(AsyncCallback<ChallengeResponseContainer> callback);

	void getHmacPassphrase(AsyncCallback<String> callback);

}

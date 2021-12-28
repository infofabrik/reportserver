package net.datenwerke.rs.authenticator.cr.client.pam;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.authenticator.client.login.dto.ChallengeResponseAuthToken;
import net.datenwerke.rs.authenticator.client.login.dto.ChallengeResponseContainer;
import net.datenwerke.rs.authenticator.client.login.pam.ClientPAM;
import net.datenwerke.rs.authenticator.client.login.pam.UserPasswordClientPAM;
import net.datenwerke.rs.authenticator.cr.client.ChallengeResponseRpcServiceAsync;
import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.ext.client.crypto.CryptoDao;
import net.datenwerke.security.ext.client.crypto.CryptoJsWrapper;

public class ChallengeResponseClientPam extends UserPasswordClientPAM {


	private final ChallengeResponseRpcServiceAsync challengeResponseService;
	private final CryptoDao cryptoService;
	private String hmacPassphrase;

	@Inject
	public ChallengeResponseClientPam(
			ChallengeResponseRpcServiceAsync challengeResponseService, 
			CryptoDao cryptoService
	) {
		this.challengeResponseService = challengeResponseService;
		this.cryptoService = cryptoService;
		this.challengeResponseService.getHmacPassphrase(new RsAsyncCallback<String>(){
			@Override
			public void onSuccess(String result) {
				hmacPassphrase = result;
			}
		});
	}

	@Override
	public void addResult(final List<AuthToken> results, final List<ClientPAM> next) {
		challengeResponseService.requestChallenge(new RsAsyncCallback<ChallengeResponseContainer>(){
			@Override
			public void onSuccess(final ChallengeResponseContainer cr) {
				cryptoService.getUserSalt(username.getText(), new RsAsyncCallback<String>(){
					@Override
					public void onSuccess(String salt) {
						ChallengeResponseAuthToken token = new ChallengeResponseAuthToken();
						token.setUsername(username.getText());
						token.setChallengeResponseContainer(cr);

						String secretStr = salt  + CryptoJsWrapper.hmac(password.getText(), hmacPassphrase + salt);
						byte[] secret = stringtoByteArray(secretStr);
						byte[] challenge = cr.getChallenge();

						byte[] concatenation = new byte[challenge.length + secret.length];
						
						{
							int n = 0;
							while(n < challenge.length){
								concatenation[n] = challenge[n];
								n++;
							}
							while(n - challenge.length < secret.length){
								concatenation[n] = secret[n - challenge.length];
								n++;
							}
						}
						
						String sha1 = CryptoJsWrapper.sha1(byteArrayToString(concatenation));
						byte[] sha1bytes = stringtoByteArray(sha1);
						
						cr.setResponse(sha1bytes);
						
						results.add(token);
						next.get(0).addResult(results, next.subList(1, next.size()));
					}
				});
			}
		});
		
	}
	
	
	
	private byte[] stringtoByteArray(String string){
		byte[] barr = new byte[string.length()];
		for(int c = 0; c < string.length(); c++){
			barr[c] = (byte) string.charAt(c);
		}
		return barr;
	}
	
	private String byteArrayToString(byte[] bytes){
		String res = "";
		for(int n = 0; n < bytes.length; n++){
			res += (char)bytes[n];
		}
		return res;
	}

}

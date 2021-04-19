package net.datenwerke.security.ext.client.password;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.security.ext.client.crypto.CryptoUIService;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.GXT;

public class PasswordServiceDao extends Dao {
	
	private final CryptoUIService cryptoService;
	private final PasswordRpcServiceAsync rpcService;

	@Inject
	public PasswordServiceDao(
		CryptoUIService cryptoService,
		PasswordRpcServiceAsync passwordService
		) {
		
		/* store objects */
		this.cryptoService = cryptoService;
		this.rpcService = passwordService;
	}
	
	public void changePassword(final String oldPassword, final String newPassword, final AsyncCallback<Void> callback){
		if(GXT.isIE()){
			//do not encrypt passwords
			rpcService.changePassword(oldPassword, newPassword, false, transformAndKeepCallback(callback));
		}else{
			cryptoService.encrypt(newPassword, oldPassword, new RsAsyncCallback<String>(){
				@Override
				public void onSuccess(final String newPasswordCipher) {
					cryptoService.encrypt(oldPassword, oldPassword, new RsAsyncCallback<String>(){
						@Override
						public void onSuccess(String oldPasswordCipher) {
							rpcService.changePassword(oldPasswordCipher, newPasswordCipher, true, transformAndKeepCallback(callback));
						}
					});
				}
			});
		}
	}

	public void changePassword(final String username, final String oldPassword, final String newPassword, final AsyncCallback<Void> callback){
		if(GXT.isIE()){
			//do not encrypt passwords
			rpcService.changePassword(username, oldPassword, newPassword, false, transformAndKeepCallback(callback));
		}else{
			cryptoService.encrypt(username, newPassword, oldPassword, new RsAsyncCallback<String>(){
				@Override
				public void onSuccess(final String newPasswordCipher) {
					cryptoService.encrypt(username, oldPassword, oldPassword, new RsAsyncCallback<String>(){
						@Override
						public void onSuccess(String oldPasswordCipher) {
							rpcService.changePassword(username, oldPasswordCipher, newPasswordCipher, true, transformAndKeepCallback(callback));
						}
					});
				}
			});
		}
	}
}

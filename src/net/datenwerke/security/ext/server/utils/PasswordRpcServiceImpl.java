package net.datenwerke.security.ext.server.utils;


import javax.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.security.ext.client.password.PasswordRpcService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

import org.apache.commons.codec.binary.Base64;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

/**
 * 
 *
 */
@Singleton
public class PasswordRpcServiceImpl extends SecuredRemoteServiceServlet implements PasswordRpcService {

	private static final long serialVersionUID = 6864534096215565334L;

	private final UserManagerService userManager;
	private final PbeService pbeService;

	private final Provider<AuthenticatorService> authServiceProvider;
	
	@Inject
	public PasswordRpcServiceImpl(
			Provider<AuthenticatorService> authServiceProvider,
			UserManagerService userManager,
			PbeService pbeService
			) {
		
		/* store objects */
		this.authServiceProvider = authServiceProvider;
		this.userManager = userManager;
		this.pbeService = pbeService;
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	public void changePassword(String oldPasswordB64, String newPasswordB64, boolean encrypted) throws ServerCallFailedException {
		String oldPassword;
		String newPassword;
		
		if(encrypted){
			/* decrypt */
			oldPassword = decrypt(oldPasswordB64);
			newPassword = decrypt(newPasswordB64);
		}else{
			oldPassword = oldPasswordB64;
			newPassword = newPasswordB64;
		}
		
		userManager.changePassword(authServiceProvider.get().getCurrentUser(), oldPassword, newPassword);
	}
	

	@Override
	@SecurityChecked(loginRequired=false)
	@Transactional(rollbackOn={Exception.class})
	public void changePassword(String username, String oldPasswordB64, String newPasswordB64, boolean encrypted) throws ExpectedException {
		User user = userManager.getUserByName(username);
		
		String oldPassword;
		String newPassword;
		
		if(encrypted){
			/* decrypt */
			oldPassword = decrypt(user, oldPasswordB64);
			newPassword = decrypt(user, newPasswordB64);
		}else{
			oldPassword = oldPasswordB64;
			newPassword = newPasswordB64;
		}

		userManager.changePassword(username, oldPassword, newPassword);
	}
	

	private String decrypt(String oldPasswordB64) {
		EncryptionService encService = pbeService.getClientEncryptionService();
		return new String(encService.decrypt(Base64.decodeBase64(oldPasswordB64.getBytes())));
	}
	
	private String decrypt(User user, String oldPasswordB64) {
		EncryptionService encService = pbeService.getClientEncryptionService(user);
		return new String(encService.decrypt(Base64.decodeBase64(oldPasswordB64.getBytes())));
	}

}

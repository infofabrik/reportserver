package net.datenwerke.rs.core.service.login;

import com.google.inject.Inject;

import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;
import net.datenwerke.security.client.login.resultinfos.AccountInhibitionAuthenticateResultInfo;
import net.datenwerke.security.service.authenticator.AuthenticationResult;
import net.datenwerke.security.service.authenticator.hooks.PostAuthenticateHook;
import net.datenwerke.security.service.usermanager.entities.User;

public class BlockSuperUserOnLoginHooker implements PostAuthenticateHook {

	public static final String BLOCK_ROOT_ACCOUNTS = "rs.authenticator.blockroot";
	
	private boolean blockRoot;
	
	@Inject
	public BlockSuperUserOnLoginHooker(ApplicationPropertiesService propService) {
		blockRoot = propService.getBoolean("rs.authenticator.blockroot", true);
	}
	
	@Override
	public void authenticated(AuthenticationResult authRes) {
		User user = authRes.getUser();
		if(null != user && user.isSuperUser() && blockRoot){
			authRes.setAllowed(false);
			authRes.addInfo(new AccountInhibitionAuthenticateResultInfo());
		}
	}

}

package net.datenwerke.rs.core.service.login;

import java.util.Date;

import net.datenwerke.rs.core.service.genrights.access.AccessRsSecurityTarget;
import net.datenwerke.rs.core.service.reportserver.ReportServerModule;
import net.datenwerke.security.client.login.resultinfos.AccountExpiredAuthenticateResultInfo;
import net.datenwerke.security.client.login.resultinfos.AccountInhibitionAuthenticateResultInfo;
import net.datenwerke.security.client.login.resultinfos.AccountMissingRightsForAuthenticateResultInfo;
import net.datenwerke.security.service.authenticator.AuthenticationResult;
import net.datenwerke.security.service.authenticator.hooks.PostAuthenticateHook;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;

import com.google.inject.Inject;

public class CheckBlockedUserOnLoginHooker implements PostAuthenticateHook {

	private final SecurityService securityService;

	@Inject
	public CheckBlockedUserOnLoginHooker(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	@Override
	public void authenticated(AuthenticationResult authRes) {
		User user = authRes.getUser();
		if(null != user) {
			if(user.isSuperUser())
				return;
			
			if(! securityService.checkRights(user, AccessRsSecurityTarget.class, SecurityServiceSecuree.class, Execute.class)){
				authRes.setAllowed(false);
				authRes.addInfo(new AccountMissingRightsForAuthenticateResultInfo());
				return;
			}
			
			UserProperty property = user.getProperty(ReportServerModule.USER_PROPERTY_ACCOUNT_INHIBITED);
			if(null != property && property.getValueAsBoolean()){
				authRes.setAllowed(false);
				authRes.addInfo(new AccountInhibitionAuthenticateResultInfo());
				return;
			}
			
			/* Check if account has expired */
			UserProperty expProperty = user.getProperty(ReportServerModule.USER_PROPERTY_ACCOUNT_EXPIRATION_DATE);
			
			try{
				if(null != expProperty){
					Date date = null != expProperty.getValue() ? new Date(Long.parseLong(expProperty.getValue())) : null;
					if(null != date && date.before(new Date())){
						authRes.setAllowed(false);
						authRes.addInfo(new AccountExpiredAuthenticateResultInfo(new Date(date.getTime() )));
						return;
					}
				}
			} catch(Exception e){
			}
		}
	}

}

package net.datenwerke.rs.authenticator.client.login.hookers;

import java.util.List;

import net.datenwerke.gxtdto.client.dialog.error.SimpleErrorDialog;
import net.datenwerke.rs.authenticator.client.login.PostAuthenticateClientHook;
import net.datenwerke.security.client.login.AuthenticateResultDto;
import net.datenwerke.security.client.login.AuthenticateResultInfo;
import net.datenwerke.security.client.login.resultinfos.AccountExpiredAuthenticateResultInfo;
import net.datenwerke.security.client.login.resultinfos.AccountInhibitionAuthenticateResultInfo;
import net.datenwerke.security.client.security.passwordpolicy.locale.PasswordPolicyMessages;

public class AccountInhibitionPostAuthenticateClientHook implements PostAuthenticateClientHook {
	
	@Override
	public void authenticated(final AuthenticateResultDto authRes, final List<PostAuthenticateClientHook> chain) {

		boolean autoProceed = true;

		if(null != authRes.getInfo()){
			for(final AuthenticateResultInfo info : authRes.getInfo()){
				if(info instanceof AccountExpiredAuthenticateResultInfo){
					autoProceed = false;
					new SimpleErrorDialog(PasswordPolicyMessages.INSTANCE.accountInhibitedHeading(), PasswordPolicyMessages.INSTANCE.accountInhibitedMessage()){
						@Override
						protected void onHide() {
							skipToEnd(authRes, chain);
						}
					}.show();
				} else if(info instanceof AccountInhibitionAuthenticateResultInfo){
					autoProceed = false;
					new SimpleErrorDialog(PasswordPolicyMessages.INSTANCE.accountInhibitedHeading(), PasswordPolicyMessages.INSTANCE.accountInhibitedMessage()) {
						@Override
						protected void onHide() {
							skipToEnd(authRes, chain);
						};
					}.show();
				} 
			}
		}

		if(autoProceed)
			proceed(authRes, chain);
	}
	
	protected void proceed(AuthenticateResultDto authRes, List<PostAuthenticateClientHook> chain){
		if(chain.size() > 0)
			chain.remove(0).authenticated(authRes, chain);
	}
	
	protected void skipToEnd(AuthenticateResultDto authRes, List<PostAuthenticateClientHook> chain){
		if(chain.size() > 0){
			PostAuthenticateClientHook last = chain.get(chain.size()-1);
			chain.clear();
			last.authenticated(authRes, chain);
		}
	}

}

package net.datenwerke.security.service.authenticator;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.security.client.login.AuthenticateResultInfo;
import net.datenwerke.security.service.usermanager.entities.User;

public class AuthenticationResult {
	
	private boolean allowed;
	private final User user;
	
	private List<AuthenticateResultInfo> infos = new ArrayList<AuthenticateResultInfo>();
	
	@Deprecated
	public AuthenticationResult(boolean valid, User user, boolean authoritative) {
		this(valid || !authoritative, user);
	}

	public AuthenticationResult(boolean allowed, User user) {
		this.allowed = allowed;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public boolean isAllowed() {
		return allowed;
	}

	public void setInfos(List<AuthenticateResultInfo> infos) {
		this.infos = infos;
	}

	public List<AuthenticateResultInfo> getInfos() {
		return infos;
	}

	public void addInfo(AuthenticateResultInfo info){
		if(null == infos)
			infos = new ArrayList<AuthenticateResultInfo>();

		infos.add(info);
	}

	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}
	
}

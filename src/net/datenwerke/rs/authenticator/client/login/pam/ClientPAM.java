package net.datenwerke.rs.authenticator.client.login.pam;

import java.util.List;

import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;

import net.datenwerke.security.client.login.AuthToken;

public interface ClientPAM {

	public interface ProceeedCallback{
		public void submit();
	}
	
	public void addFields(FlowLayoutContainer container, ProceeedCallback callback);
	
	public void addResult(List<AuthToken> results, List<ClientPAM> next);
	
	public String getModuleName();
	
}

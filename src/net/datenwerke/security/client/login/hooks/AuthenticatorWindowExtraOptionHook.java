package net.datenwerke.security.client.login.hooks;

import com.sencha.gxt.widget.core.client.container.Container;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface AuthenticatorWindowExtraOptionHook extends Hook {
	
	public enum ExtraOptionPosition{
		TOP,
		FIELD
	}
	
	public void configure(Container extraOptionsPanel);

	public ExtraOptionPosition getPosition();
}

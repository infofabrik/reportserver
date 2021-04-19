package net.datenwerke.security.service.usermanager.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;

public interface UserPropertyChangedHook extends Hook {

	public void beforeUserPropertyChange(User user, UserProperty oldValue, UserProperty newValue);
	
	public void afterUserPropertyChange(User user, UserProperty oldValue, UserProperty newValue);	
}

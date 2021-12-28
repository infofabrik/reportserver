package net.datenwerke.security.service.usermanager.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.security.service.usermanager.entities.User;

public interface PasswordSetHook extends Hook {

   public void passwordWasSet(User user);

}

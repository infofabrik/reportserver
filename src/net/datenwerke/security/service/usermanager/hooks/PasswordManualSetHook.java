package net.datenwerke.security.service.usermanager.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.security.service.usermanager.entities.User;

public interface PasswordManualSetHook extends Hook {

   public void passwordWasManuallySet(User user, boolean createdPassword);

}

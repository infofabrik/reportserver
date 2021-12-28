package net.datenwerke.security.service.usermanager.hooks;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.security.service.usermanager.entities.User;

@HookConfig
public interface ChangePasswordHook extends Hook {

   public void beforePasswordChanged(User user, String newPassword) throws ExpectedException;

   public void afterPasswordChanged(User user);

}

package net.datenwerke.rs.passwordpolicy.service.lostpassword;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.service.authenticator.hooks.PreAuthenticateHook;

public class LostPasswordModuleStartup {

   @Inject
   public LostPasswordModuleStartup(HookHandlerService hookHandler,
         LostPasswordPreAuthenticateHook lostPasswordPreAuthenticateHook) {
      hookHandler.attachHooker(PreAuthenticateHook.class, lostPasswordPreAuthenticateHook);
   }

}

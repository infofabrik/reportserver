package net.datenwerke.rs.passwordpolicy.client;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.authenticator.client.login.PostAuthenticateClientHook;

public class PasswordPolicyUIStartup {

   @Inject
   public PasswordPolicyUIStartup(HookHandlerService hookHandlerService,
         BsiPasswordPolicyPostAuthenticateClientHook bsiPolicyAuthenticateHook) {

      hookHandlerService.attachHooker(PostAuthenticateClientHook.class, bsiPolicyAuthenticateHook);
   }

}

package net.datenwerke.rs.passwordpolicy.client;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.authenticator.client.login.PostAuthenticateClientHook;

import com.google.inject.Inject;

public class PasswordPolicyUIStartup {

   @Inject
   public PasswordPolicyUIStartup(HookHandlerService hookHandlerService,
         BsiPasswordPolicyPostAuthenticateClientHook bsiPolicyAuthenticateHook) {

      hookHandlerService.attachHooker(PostAuthenticateClientHook.class, bsiPolicyAuthenticateHook);
   }

}

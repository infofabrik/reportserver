package net.datenwerke.rs.authenticator.cr.client;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.authenticator.client.login.hooks.ClientPAMHook;
import net.datenwerke.rs.authenticator.cr.client.pam.ChallengeResponseClientPam;

public class ChallengeResponseUIStartup {

   @Inject
   public ChallengeResponseUIStartup(Provider<ChallengeResponseClientPam> challengeResponseClientPamProvider,
         HookHandlerService hookHandler) {

      hookHandler.attachHooker(ClientPAMHook.class, new ClientPAMHook(challengeResponseClientPamProvider));
   }
}

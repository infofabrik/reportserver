package net.datenwerke.rs.ldapserver.client.ldapserver;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.ldapserver.client.ldapserver.hookers.LdapConfigProviderHooker;

public class LdapServerUIStartup {

   @Inject
   public LdapServerUIStartup(
         final HookHandlerService hookHandler,
         final Provider<LdapConfigProviderHooker> ldapConfigProviderHooker//,
       //  final RemoteRsRestServerTesterToolbarConfigurator testToolbarConfigurator
         ) {

    //  will be available in 4.8
//      hookHandler.attachHooker(RemoteServerDefinitionConfigProviderHook.class, ldapConfigProviderHooker.get());
      
    //  hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, testToolbarConfigurator);
   }

}
package net.datenwerke.rs.ldap.service.ldap;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.ldap.service.ldap.terminal.commands.LdapimportCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

public class LdapStartup {

   @Inject
   public LdapStartup(
         HookHandlerService hookHandler,
         
         Provider<LdapimportCommand> ldapCommandProvider
         ) {
      
      hookHandler.attachHooker(TerminalCommandHook.class, ldapCommandProvider);
   }
}

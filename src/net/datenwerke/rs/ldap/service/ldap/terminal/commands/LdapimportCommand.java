package net.datenwerke.rs.ldap.service.ldap.terminal.commands;

import javax.inject.Inject;

import net.datenwerke.rs.ldap.service.ldap.LdapService;
import net.datenwerke.rs.ldap.service.ldap.exceptions.LdapException;
import net.datenwerke.rs.ldap.service.ldap.locale.LdapMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class LdapimportCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "ldapimport";

   private final LdapService ldapService;

   @Inject
   public LdapimportCommand(LdapService ldapService) {
      this.ldapService = ldapService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(
         messageClass = LdapMessages.class, 
         name = BASE_COMMAND, 
         description = "commandLdapimport_description")
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      try {
         ldapService.importUsers();
      } catch (LdapException e) {
         throw new TerminalException("LDAP import was not possible: " + e);
      }
      return new CommandResult("LDAP import successful");
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}

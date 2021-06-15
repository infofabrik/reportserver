package net.datenwerke.rs.terminal.service.terminal.basecommands;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.basecommands.hooks.CatCommandHandlerHook;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.security.service.security.rights.Read;

public class CatCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "cat";

   private final HookHandlerService hookHandler;

   @Inject
   public CatCommand(HookHandlerService hookHandler) {

      /* store objects */
      this.hookHandler = hookHandler;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   @CliHelpMessage(messageClass = TerminalMessages.class, name = BASE_COMMAND, description = "commandCat_description")
   public CommandResult execute(final CommandParser parser, final TerminalSession session) throws TerminalException {
      String argument = parser.getArgumentString();
      final Object object = session.getObjectResolver().getObject(argument, Read.class);

      if (null != object) {
         return hookHandler.getHookers(CatCommandHandlerHook.class)
            .stream()
            .filter(hook -> hook.consumes(object, parser))
            .map(hook -> hook.cat(object, parser))
            .findAny()
            .orElseGet(() -> new CommandResult(TerminalMessages.INSTANCE.cannotCatObject()));
      }
         
      return new CommandResult(TerminalMessages.INSTANCE.cannotCatObject());
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}

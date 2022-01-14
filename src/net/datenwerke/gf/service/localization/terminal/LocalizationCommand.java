package net.datenwerke.gf.service.localization.terminal;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gf.service.localization.locale.LocalizationMessages;
import net.datenwerke.gf.service.localization.terminal.hooks.LocalizationSubCommandHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommandContainerImpl;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class LocalizationCommand extends SubCommandContainerImpl {

   public static final String BASE_COMMAND = "localization";

   private final HookHandlerService hookHandler;

   @Inject
   public LocalizationCommand(HookHandlerService hookHandler) {

      /* store objects */
      this.hookHandler = hookHandler;
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

   @Override
   @CliHelpMessage(messageClass = LocalizationMessages.class, name = BASE_COMMAND, description = "commandLocalization_description")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      return super.execute(parser, session);
   }

   @Override
   public List<SubCommand> getSubCommands() {
      List<LocalizationSubCommandHook> list = hookHandler.getHookers(LocalizationSubCommandHook.class);
      return new ArrayList<SubCommand>(list);
   }

}

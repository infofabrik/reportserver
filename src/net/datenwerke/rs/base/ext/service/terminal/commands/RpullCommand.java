package net.datenwerke.rs.base.ext.service.terminal.commands;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.ext.service.locale.RsBaseExtMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommandContainerImpl;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class RpullCommand extends SubCommandContainerImpl {

   public static final String BASE_COMMAND = "rpull";

   private final HookHandlerService hookHandler;

   @Inject
   public RpullCommand(HookHandlerService hookHandler) {
      this.hookHandler = hookHandler;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(
         messageClass = RsBaseExtMessages.class, 
         name = BASE_COMMAND, 
         description = "rpullDesc"
   )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      return super.execute(parser, session);
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

   @Override
   public List<SubCommand> getSubCommands() {
      return hookHandler.getHookers(RpullSubCommandHook.class);
   }
}

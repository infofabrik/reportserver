package net.datenwerke.rs.transport.service.transport.terminal.commands;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommandContainerImpl;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.transport.service.transport.locale.TransportManagerMessages;

public class TransportCommand extends SubCommandContainerImpl {

   public static final String BASE_COMMAND = "transport";

   private final HookHandlerService hookHandler;

   @Inject
   public TransportCommand(HookHandlerService hookHandler) {
      this.hookHandler = hookHandler;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(
         messageClass = TransportManagerMessages.class, 
         name = BASE_COMMAND, 
         description = "transportDesc"
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
      return hookHandler.getHookers(TransportSubCommandHook.class);
   }
}

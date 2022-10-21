package net.datenwerke.rs.incubator.service.versioning.terminal.commands;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.incubator.service.versioning.hooks.RevSubCommandHook;
import net.datenwerke.rs.incubator.service.versioning.locale.VersioningMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommandContainerImpl;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class RevCommand extends SubCommandContainerImpl {

   public static final String BASE_COMMAND = "rev";

   private final HookHandlerService hookHandler;

   @Inject
   public RevCommand(HookHandlerService hookHandler) {
      this.hookHandler = hookHandler;
   }

   @Override
   @CliHelpMessage(messageClass = VersioningMessages.class, name = BASE_COMMAND, description = "commandRev_description")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      return super.execute(parser, session);
   }

   @Override
   public List<SubCommand> getSubCommands() {
      List<RevSubCommandHook> list = hookHandler.getHookers(RevSubCommandHook.class);
      return new ArrayList<SubCommand>(list);
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

}

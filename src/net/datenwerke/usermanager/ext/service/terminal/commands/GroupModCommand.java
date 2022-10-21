package net.datenwerke.usermanager.ext.service.terminal.commands;

import java.util.ArrayList;
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
import net.datenwerke.security.service.usermanager.hooks.GroupModSubCommandHook;
import net.datenwerke.security.service.usermanager.locale.UserManagerMessages;

public class GroupModCommand extends SubCommandContainerImpl {

   public static final String BASE_COMMAND = "groupmod";

   private final HookHandlerService hookHandler;

   @Inject
   public GroupModCommand(HookHandlerService hookHandler) {

      /* store objects */
      this.hookHandler = hookHandler;
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

   @Override
   @CliHelpMessage(messageClass = UserManagerMessages.class, name = BASE_COMMAND, description = "commandGroupmod_description")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      return super.execute(parser, session);
   }

   @Override
   public List<SubCommand> getSubCommands() {
      List<GroupModSubCommandHook> list = hookHandler.getHookers(GroupModSubCommandHook.class);
      return new ArrayList<SubCommand>(list);
   }

}

package net.datenwerke.rs.teamspace.service.teamspace.terminal.commands;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.teamspace.service.teamspace.hooks.TeamspaceModSubCommandHook;
import net.datenwerke.rs.teamspace.service.teamspace.locale.TeamSpaceMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommandContainerImpl;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class TeamspaceModCommand extends SubCommandContainerImpl {

   public static final String BASE_COMMAND = "teamspacemod";

   private final HookHandlerService hookHandler;

   @Inject
   public TeamspaceModCommand(HookHandlerService hookHandler) {

      /* store objects */
      this.hookHandler = hookHandler;
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

   @Override
   @CliHelpMessage(messageClass = TeamSpaceMessages.class, name = BASE_COMMAND, description = "commandTeamspacemod_description")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      return super.execute(parser, session);
   }

   @Override
   public List<SubCommand> getSubCommands() {
      List<TeamspaceModSubCommandHook> list = hookHandler.getHookers(TeamspaceModSubCommandHook.class);
      return new ArrayList<SubCommand>(list);
   }

}

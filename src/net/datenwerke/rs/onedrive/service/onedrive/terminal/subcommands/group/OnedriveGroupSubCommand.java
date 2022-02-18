package net.datenwerke.rs.onedrive.service.onedrive.terminal.subcommands.group;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.locale.ConfigMessages;
import net.datenwerke.rs.onedrive.service.onedrive.terminal.OnedriveSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubSubCommandContainerImpl;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class OnedriveGroupSubCommand extends SubSubCommandContainerImpl implements OnedriveSubCommandHook{
   public static final String BASE_COMMAND = "group";
   private final HookHandlerService hookHandler;
   
   
   @Inject
   public OnedriveGroupSubCommand(HookHandlerService hookHandler) {
      this.hookHandler = hookHandler;
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }
   
   @Override
   @CliHelpMessage(messageClass = ConfigMessages.class, name = BASE_COMMAND, description = "commandConfig_description")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      return super.execute(parser, session);
   }

   @Override
   public List<SubCommand> getSubCommands() {
      List<SubCommand> subCommands = hookHandler.getHookers(OnedriveGroupSubSubCommandHook.class);
      return new ArrayList<SubCommand>(subCommands);
   }
}

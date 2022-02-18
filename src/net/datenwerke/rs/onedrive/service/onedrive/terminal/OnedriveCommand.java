package net.datenwerke.rs.onedrive.service.onedrive.terminal;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.locale.ConfigMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommandContainerImpl;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class OnedriveCommand extends SubCommandContainerImpl{
   
   public static final String BASE_COMMAND = "onedrive";
   private final HookHandlerService hookHandler;
   
   
   @Inject
   public OnedriveCommand(HookHandlerService hookHandler) {
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
      List<OnedriveSubCommandHook> list = hookHandler.getHookers(OnedriveSubCommandHook.class);
      return new ArrayList<SubCommand>(list);
   }

}

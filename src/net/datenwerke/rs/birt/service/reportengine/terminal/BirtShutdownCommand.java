package net.datenwerke.rs.birt.service.reportengine.terminal;

import com.google.inject.Inject;

import net.datenwerke.rs.birt.service.reportengine.BirtReportService;
import net.datenwerke.rs.birt.service.reportengine.locale.BirtEngineMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.utils.string.Emoji;

public class BirtShutdownCommand implements BirtSubCommandHook {

   public static final String BASE_COMMAND = "shutdown";

   private final BirtReportService birtService;

   @Inject
   public BirtShutdownCommand(BirtReportService birtService) {
      this.birtService = birtService;

   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   @CliHelpMessage(messageClass = BirtEngineMessages.class, name = BASE_COMMAND, description = "commandBirt_sub_shutdown_description")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
      birtService.shutdown();

      return new CommandResult(Emoji.STOP_SIGN.getEmoji(" ") + BirtEngineMessages.INSTANCE.shutdownComplete());
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}

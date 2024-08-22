package net.datenwerke.rs.base.ext.service.reportmanager.terminal.commands;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.base.ext.service.reportmanager.hooks.ReportModSubCommandHook;
import net.datenwerke.rs.base.ext.service.reportmanager.locale.ReportManagerExtMessages;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.utils.string.Emoji;
import net.datenwerke.security.service.security.rights.Read;

public class SetUuidCommand implements ReportModSubCommandHook {

   public static final String BASE_COMMAND = "setUUID";

   private final ReportService reportService;

   @Inject
   public SetUuidCommand(ReportService reportService) {

      /* store objects */
      this.reportService = reportService;
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
   @CliHelpMessage(messageClass = ReportManagerExtMessages.class, name = BASE_COMMAND, description = "commandReportmod_sub_setUUID_description", nonOptArgs = {
         @NonOptArgument(name = "UUID", description = "commandReportmod_sub_setUUID_arg1", mandatory = true) })
   public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
      List<String> arguments = parser.getNonOptionArguments();
      if (2 > arguments.size())
         throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji());

      String reportRef = arguments.get(0);
      String uuid = arguments.get(1);

      try {
         Report report = (Report) session.getObjectResolver().getObject(reportRef, Read.class);
         if (null == report)
            return new CommandResult(Emoji.SMILING_FACE_TEAR.getEmoji(" ") + "Coud not find report for " + reportRef);

         report.setUuid(uuid);
         reportService.merge(report);

         return new CommandResult(Emoji.BEER_MUG.getEmoji(" ") + "Set uuid to " + uuid);
      } catch (Exception e) {
         return new CommandResult(Emoji.SMILING_FACE_TEAR.getEmoji(" ") + "Coud not find report for " + reportRef);
      }
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}

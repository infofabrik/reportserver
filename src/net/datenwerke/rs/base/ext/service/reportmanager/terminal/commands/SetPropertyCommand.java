package net.datenwerke.rs.base.ext.service.reportmanager.terminal.commands;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.base.ext.service.reportmanager.hooks.ReportModSubCommandHook;
import net.datenwerke.rs.base.ext.service.reportmanager.locale.ReportManagerExtMessages;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportStringProperty;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.utils.string.Emoji;
import net.datenwerke.security.service.security.rights.Read;

public class SetPropertyCommand implements ReportModSubCommandHook {

   public static final String BASE_COMMAND = "setProperty";

   private final ReportService reportService;

   @Inject
   public SetPropertyCommand(ReportService reportService) {

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
   @CliHelpMessage(messageClass = ReportManagerExtMessages.class, name = BASE_COMMAND, description = "commandReportmod_sub_setProperty_description", nonOptArgs = {
         @NonOptArgument(name = "identifier", description = "commandReportmod_sub_setProperty_arg1", mandatory = true),
         @NonOptArgument(name = "property", description = "commandReportmod_sub_setProperty_arg2", mandatory = true),
         @NonOptArgument(name = "value", description = "commandReportmod_sub_setProperty_arg3", mandatory = true, varArgs = true) })
   public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
      List<String> arguments = parser.getNonOptionArguments();
      if (3 > arguments.size())
         throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji());

      String reportRef = arguments.get(0);
      String propertyName = arguments.get(1);
      String value = arguments.get(2);

      try {
         Report report = (Report) session.getObjectResolver().getObject(reportRef, Read.class);
         if (null == report)
            return new CommandResult(Emoji.SMILING_FACE_TEAR.getEmoji(" ") + "Coud not find report for " + reportRef);

         ReportProperty property = report.getReportProperty(propertyName);
         if (property instanceof ReportStringProperty)
            ((ReportStringProperty) property).setStrValue(value);
         else if (null == property) {
            property = new ReportStringProperty();
            property.setName(propertyName);
            ((ReportStringProperty) property).setStrValue(value);

            report.addReportProperty(property);
         } else {
            return new CommandResult(Emoji.SMILING_FACE_TEAR.getEmoji(" ") + "Specified property is not a simple property");
         }

         reportService.merge(report);

         return new CommandResult(Emoji.CLINKING_GLASSES.getEmoji(" ") + "Property changed");
      } catch (Exception e) {
         return new CommandResult(Emoji.SMILING_FACE_TEAR.getEmoji(" ") + "Coud not find report for " + reportRef);
      }
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}

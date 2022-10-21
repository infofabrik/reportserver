package net.datenwerke.rs.base.ext.service.reportmanager.terminal.commands;

import java.util.Arrays;
import java.util.List;

import net.datenwerke.rs.base.ext.service.reportmanager.hooks.ReportModSubCommandHook;
import net.datenwerke.rs.base.ext.service.reportmanager.locale.ReportManagerExtMessages;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
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
import net.datenwerke.security.service.security.rights.Read;

public class ListPropertyCommand implements ReportModSubCommandHook {

   public static final String BASE_COMMAND = "listProperties";

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   @CliHelpMessage(messageClass = ReportManagerExtMessages.class, name = BASE_COMMAND, description = "commandReportmod_sub_listProperties_description", nonOptArgs = {
         @NonOptArgument(name = "identifier", description = "commandReportmod_sub_listProperties_arg1", mandatory = true),
         @NonOptArgument(name = "property", description = "commandReportmod_sub_listProperties_arg2", mandatory = true) })
   public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
      List<String> arguments = parser.getNonOptionArguments();
      if (1 > arguments.size())
         throw new IllegalArgumentException();

      String reportRef = arguments.get(0);
      String propertyName = null;
      if (arguments.size() >= 2)
         propertyName = arguments.get(1);

      try {
         Report report = (Report) session.getObjectResolver().getObject(reportRef, Read.class);
         if (null == report)
            return new CommandResult("Coud not find report for " + reportRef);

         RSTableModel model = new RSTableModel();
         model.setTableDefinition(new TableDefinition(Arrays.asList(new String[] { "property", "value" })));

         if (null != propertyName) {
            ReportProperty property = report.getReportProperty(propertyName);
            if (property instanceof ReportStringProperty) {
               model.addDataRow(
                     new RSStringTableRow(property.getName(), ((ReportStringProperty) property).getStrValue()));
            } else if (null != property) {
               model.addDataRow(new RSStringTableRow(property.getName(), ""));
            } else {
               return new CommandResult("Specified property does not exist");
            }
            return new CommandResult(model);
         } else {
            for (ReportProperty property : report.getReportProperties()) {
               if (property instanceof ReportStringProperty)
                  model.addDataRow(
                        new RSStringTableRow(property.getName(), ((ReportStringProperty) property).getStrValue()));
               else
                  model.addDataRow(new RSStringTableRow(property.getName(), ""));
            }

            return new CommandResult(model);
         }
      } catch (Exception e) {
         return new CommandResult("Coud not find report for " + reportRef);
      }
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}

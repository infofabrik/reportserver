package net.datenwerke.rs.condition.service.condition.terminal.commands;

import java.util.Arrays;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.reportengines.table.TableModelHelper;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.condition.service.condition.ConditionService;
import net.datenwerke.rs.condition.service.condition.entity.ReportCondition;
import net.datenwerke.rs.condition.service.condition.locale.ConditionMessages;
import net.datenwerke.rs.condition.service.condition.terminal.commands.hooks.ConditionSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;

public class ListConditionCommand implements ConditionSubCommandHook {

   public static final String BASE_COMMAND = "list";

   private final ConditionService conditionService;

   @Inject
   public ListConditionCommand(ConditionService conditionService, TableModelHelper tableModelHelper) {

      /* store objects */
      this.conditionService = conditionService;
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
   @CliHelpMessage(messageClass = ConditionMessages.class, name = BASE_COMMAND, description = "commandRcondition_sub_list_desc")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
      RSTableModel table = new RSTableModel();
      table.setTableDefinition(
            new TableDefinition(Arrays.asList(new String[] { "id", "name", "key", "description", "report" })));

      for (ReportCondition cond : conditionService.getReportConditions())
         table.addDataRow(new RSStringTableRow(cond.getId().toString(), cond.getName(), cond.getKey(),
               cond.getDescription(), null != cond.getReport() ? cond.getReport().getId().toString() : "null"));

      return new CommandResult(table);
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

}

package net.datenwerke.rs.terminal.service.terminal.basecommands;

import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.FREE_FORMATTED;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.MAX_FORMATTED;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.TOTAL_FORMATTED;
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.USED_FORMATTED;

import java.util.Arrays;
import java.util.Map;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class MeminfoCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "meminfo";
   
   private final Provider<GeneralInfoService> generalInfoServiceProvider;
   
   @Inject
   public MeminfoCommand(
         Provider<GeneralInfoService> generalInfoServiceProvider
         ) {
      this.generalInfoServiceProvider = generalInfoServiceProvider;
   }

   @Override
   @CliHelpMessage(messageClass = TerminalMessages.class, name = BASE_COMMAND, description = "commandMeminfo_description")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      if (parser.hasOption("g", "g?")) {
         System.gc();
         System.gc();
      }

      RSTableModel table = new RSTableModel();
      TableDefinition td = new TableDefinition(Arrays.asList("Heap utilization statistics", "MB"),
            Arrays.asList(String.class, String.class));
      table.setTableDefinition(td);

      Map<Memory, Object> memory = generalInfoServiceProvider.get().getMemoryValues();
      table.addDataRow(new RSStringTableRow("Used Memory", (String)memory.get(USED_FORMATTED)));
      table.addDataRow(new RSStringTableRow("Free Memory", (String)memory.get(FREE_FORMATTED)));
      table.addDataRow(new RSStringTableRow("Total Memory", (String)memory.get(TOTAL_FORMATTED)));
      table.addDataRow(new RSStringTableRow("Max Memory", (String)memory.get(MAX_FORMATTED)));

      CommandResult result = new CommandResult();
      result.addResultTable(table);
      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

}

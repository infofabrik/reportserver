package net.datenwerke.rs.terminal.service.terminal.basecommands;

import java.util.Arrays;

import com.google.inject.Inject;
import com.google.inject.Provider;

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

public class EnvCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "env";
   private final Provider<GeneralInfoService> generalInfoServiceProvider;

   @Inject
   public EnvCommand(Provider<GeneralInfoService> generalInfoServiceProvider) {
      this.generalInfoServiceProvider = generalInfoServiceProvider;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }
   
   @Override
   @CliHelpMessage(
         messageClass = TerminalMessages.class,
         name = BASE_COMMAND,
         description = "commandEnv_description"
   )
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      CommandResult result = new CommandResult();

      GeneralInfoService generalInfoService = generalInfoServiceProvider.get();

      RSTableModel table = new RSTableModel();
      TableDefinition td = new TableDefinition(Arrays.asList("General Info", ""),
            Arrays.asList(String.class, String.class, String.class));
      table.setTableDefinition(td);

      table.addDataRow(new RSStringTableRow("Version", generalInfoService.getRsVersion()));
      table.addDataRow(new RSStringTableRow("Java version", generalInfoService.getJavaVersion()));
      table.addDataRow(new RSStringTableRow("JVM Args", generalInfoService.getVmArguments()));
      table.addDataRow(new RSStringTableRow("Application server", generalInfoService.getApplicationServer()));
      table.addDataRow(new RSStringTableRow("Operation system", generalInfoService.getOsVersion()));
      table.addDataRow(new RSStringTableRow("Browser", generalInfoService.getBrowserName()));
      table.addDataRow(new RSStringTableRow("Browser version", generalInfoService.getBrowserVersion()));

      result.addResultTable(table);

      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}

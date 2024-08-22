package net.datenwerke.rs.terminal.service.terminal.basecommands;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.GeneralInfoUiModule;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.client.helper.ObjectHolder;
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
   public EnvCommand(
         Provider<GeneralInfoService> generalInfoServiceProvider
         ) {
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
      final CommandResult result = new CommandResult();
      final Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> generalInfo = generalInfoServiceProvider
            .get().getGeneralInfo();

      generalInfo.forEach((category, props) -> addCategoryToResult(category, props, result));
      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }
   
   private void addCategoryToResult(ImmutablePair<String, String> category,
         Map<ImmutablePair<String, String>, Object> properties, final CommandResult result) {
      RSTableModel table = new RSTableModel();
      TableDefinition td = new TableDefinition(Arrays.asList(category.getRight(), ""),
            Arrays.asList(String.class, String.class));
      td.setDisplaySizes(Arrays.asList(200, 0));
      table.setTableDefinition(td);
      
      final ObjectHolder<Boolean> disabled = new ObjectHolder<>();
      final ObjectHolder<String> disabledMsg = new ObjectHolder<>();
      disabled.set(false);
      properties.forEach((key, val) -> {
         if (key.getRight().equals(GeneralInfoUiModule.ENABLED) && val.equals(false))
            disabled.set(true);
         if (key.getRight().equals(GeneralInfoUiModule.DISABLED_MESSAGE))
            disabledMsg.set(val.toString());
      });
      
      if (!disabled.get()) {
         properties.forEach((key, val) -> {
            if (!key.getRight().equals(GeneralInfoUiModule.ENABLED)
                  && !key.getRight().equals(GeneralInfoUiModule.DISABLED_MESSAGE))
               table.addDataRow(new RSStringTableRow(key.getRight(), null != val ? val.toString(): null));
         });
      } else {
         table.addDataRow(new RSStringTableRow("Message", disabledMsg.get()));
      }
      result.addResultTable(table);
   }
   
}

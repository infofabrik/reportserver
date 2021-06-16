package net.datenwerke.rs.incubator.service.aliascmd.terminal.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.datenwerke.rs.configservice.service.configservice.hooks.ReloadConfigNotificationHook;
import net.datenwerke.rs.incubator.service.aliascmd.AliasCmdModule;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.utils.config.ConfigService;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class AliasCommand implements TerminalCommandHook, ReloadConfigNotificationHook {

   public static final String ENTRY_PROPERTY_COMMAND = "command";
   public static final String ENTRY_PROPERTY_ALIAS = "alias";
   public static final String ENTRY_PROPERTY = "cmdaliases.entry";

   private final ConfigService configService;
   private final Map<String, String> aliasTable = new HashMap<>();
   
   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   @Inject
   public AliasCommand(ConfigService configService) {
      this.configService = configService;
   }

   public void updateConfiguration() {
      try {
         XMLConfiguration config = (XMLConfiguration) configService.getConfig(AliasCmdModule.CONFIG_FILE);
         aliasTable.clear();

         List<HierarchicalConfiguration> fields = config.configurationsAt(ENTRY_PROPERTY);
         for (HierarchicalConfiguration sub : fields) {
            String alias = sub.getString(ENTRY_PROPERTY_ALIAS);
            String cmd = sub.getString(ENTRY_PROPERTY_COMMAND);
            aliasTable.put(alias, cmd);
         }
      } catch (Exception e) {
         logger.info("Error updating alias command configuration", e);
      }
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      String cmd = parser.getBaseCommand();
      return aliasTable.containsKey(cmd);
   }

   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      String alias = aliasTable.get(parser.getBaseCommand());
      String newCommand = alias + " " + parser.getArgumentString();

      return session.execute(newCommand);
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      for (String alias : aliasTable.keySet())
         autocompleteHelper.autocompleteBaseCommand(alias);
   }

   @Override
   public void reloadConfig() {
      updateConfiguration();
   }

   @Override
   public void reloadConfig(String identifier) {
      if (AliasCmdModule.CONFIG_FILE.equals(identifier))
         updateConfiguration();
   }

}

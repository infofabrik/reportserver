package net.datenwerke.gf.service.properties;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.properties.terminal.commands.PropertiesClearSubCommand;
import net.datenwerke.gf.service.properties.terminal.commands.PropertiesCommand;
import net.datenwerke.gf.service.properties.terminal.commands.PropertiesContainsSubCommand;
import net.datenwerke.gf.service.properties.terminal.commands.PropertiesListSubCommand;
import net.datenwerke.gf.service.properties.terminal.commands.PropertiesPutSubCommand;
import net.datenwerke.gf.service.properties.terminal.commands.PropertiesRemoveSubCommand;
import net.datenwerke.gf.service.properties.terminal.hooks.PropertiesSubCommandHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

public class PropertiesStartup {

   @Inject
   public PropertiesStartup(
         final HookHandlerService hookHandler,
         
         final Provider<PropertiesCommand> propertiesCommand,
         final Provider<PropertiesListSubCommand> propertiesListCommand,
         final Provider<PropertiesClearSubCommand> propertiesRemoveAllCommand,
         final Provider<PropertiesRemoveSubCommand> propertiesRemoveCommand,
         final Provider<PropertiesPutSubCommand> propertiesPutCommand,
         final Provider<PropertiesContainsSubCommand> propertiesContainsCommand
         ) {

      hookHandler.attachHooker(TerminalCommandHook.class, propertiesCommand);
      hookHandler.attachHooker(PropertiesSubCommandHook.class, propertiesListCommand);
      hookHandler.attachHooker(PropertiesSubCommandHook.class, propertiesRemoveAllCommand);
      hookHandler.attachHooker(PropertiesSubCommandHook.class, propertiesRemoveCommand);
      hookHandler.attachHooker(PropertiesSubCommandHook.class, propertiesPutCommand);
      hookHandler.attachHooker(PropertiesSubCommandHook.class, propertiesContainsCommand);

   }
}

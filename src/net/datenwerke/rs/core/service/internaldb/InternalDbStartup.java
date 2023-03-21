package net.datenwerke.rs.core.service.internaldb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.gf.service.lifecycle.hooks.ContextHook;
import net.datenwerke.gf.service.maintenance.hooks.MaintenanceTask;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.internaldb.hookers.ClearInternalDbOnShutdown;
import net.datenwerke.rs.core.service.internaldb.hookers.GeneralInfoInternalDbCategoryProviderHooker;
import net.datenwerke.rs.core.service.internaldb.hookers.TempTableMaintenanceTask;
import net.datenwerke.rs.core.service.internaldb.terminal.commands.ClearInternalDbCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

public class InternalDbStartup {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private static final String CONFIG_FILE = "datasources/internaldb.cf";
   protected static final String DROP_TMPTABLES = "internaldb.droponstartup";

   @Inject
   public InternalDbStartup(
         final HookHandlerService hookHandler, 
         final ClearInternalDbOnShutdown shutdownHooker,
         final TempTableService tempTableService, 
         final ConfigService configService,
         final TempTableMaintenanceTask tempTableMaintencanceTask,
         final Provider<ClearInternalDbCommand> clearInternalDbCommand,
         final Provider<GeneralInfoInternalDbCategoryProviderHooker> generalInfoInternalDbCategoryProviderHooker
   ) {

      hookHandler.attachHooker(ContextHook.class, shutdownHooker);

      hookHandler.attachHooker(MaintenanceTask.class, tempTableMaintencanceTask);
      
      hookHandler.attachHooker(TerminalCommandHook.class, clearInternalDbCommand);
      
      hookHandler.attachHooker(GeneralInfoCategoryProviderHook.class, generalInfoInternalDbCategoryProviderHooker,
            HookHandlerService.PRIORITY_LOW + 30);

      hookHandler.attachHooker(LateInitHook.class, () -> {
         try {
            if (configService.getConfigFailsafe(CONFIG_FILE).getBoolean(DROP_TMPTABLES, true))
               tempTableService.dropRSTMPtables();

         } catch (Exception e) {
            logger.error("Error in Internal DB Startup", e);
         }
      });
   }
}

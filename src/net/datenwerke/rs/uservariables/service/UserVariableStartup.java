package net.datenwerke.rs.uservariables.service;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterProviderHook;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.rs.uservariables.service.eventhandler.HandleUserNodeRemoveEvents;
import net.datenwerke.rs.uservariables.service.eventhandler.HandleUserVarForceRemoveEvents;
import net.datenwerke.rs.uservariables.service.eventhandler.HandleUserVarRemoveEvents;
import net.datenwerke.rs.uservariables.service.parameters.hookers.UserVariableParameterProviderHooker;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.eximport.UserVariableExporter;
import net.datenwerke.rs.uservariables.service.uservariables.eximport.UserVariableImporter;
import net.datenwerke.rs.uservariables.service.uservariables.eximport.hookers.ExportAllUserVariablesHooker;
import net.datenwerke.rs.uservariables.service.uservariables.eximport.hookers.ImportAllUserVariablesHooker;
import net.datenwerke.rs.uservariables.service.uservariables.hookers.BaseUserVariableProvider;
import net.datenwerke.rs.uservariables.service.uservariables.hookers.UsageStatisticsUserVariableDefinitionProviderHooker;
import net.datenwerke.rs.uservariables.service.uservariables.hookers.UsageStatisticsUserVariableInstanceProviderHooker;
import net.datenwerke.rs.uservariables.service.uservariables.hookers.UserVariableCategoryProviderHooker;
import net.datenwerke.rs.uservariables.service.uservariables.hooks.UserVariableEntryProviderHook;
import net.datenwerke.rs.uservariables.service.uservariables.hooks.UserVariableProviderHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

public class UserVariableStartup {

   @Inject
   public UserVariableStartup(HookHandlerService hookHandler, 
         EventBus eventBus,

         Provider<UserVariableExporter> exporterProvider, 
         Provider<UserVariableImporter> importerProvider,
         Provider<ExportAllUserVariablesHooker> exportAllUserVars,
         Provider<ImportAllUserVariablesHooker> importAllUserVars,

         Provider<BaseUserVariableProvider> baseVariableProvider,

         Provider<UserVariableParameterProviderHooker> parameterProvider,

         HandleUserVarRemoveEvents handleRemove, 
         HandleUserVarForceRemoveEvents handleForceRemove,

         HandleUserNodeRemoveEvents handleFolkRemove,
         
         final Provider<UserVariableCategoryProviderHooker> usageStatistics,
         final Provider<UsageStatisticsUserVariableDefinitionProviderHooker> usageStatsDefinitionProvider,
         final Provider<UsageStatisticsUserVariableInstanceProviderHooker> usageStatsInstanceProvider
   ) {

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, UserVariableDefinition.class, handleRemove);
      eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, UserVariableDefinition.class, handleForceRemove);

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, AbstractUserManagerNode.class, handleFolkRemove);

      /* eximport */
      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(exporterProvider));
      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(importerProvider));
      hookHandler.attachHooker(ExportAllHook.class, exportAllUserVars);
      hookHandler.attachHooker(ImportAllHook.class, importAllUserVars);

      hookHandler.attachHooker(UserVariableProviderHook.class, baseVariableProvider);

      hookHandler.attachHooker(ParameterProviderHook.class, parameterProvider);
      
      hookHandler.attachHooker(UserVariableEntryProviderHook.class, usageStatsDefinitionProvider,
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(UserVariableEntryProviderHook.class, usageStatsInstanceProvider,
            HookHandlerService.PRIORITY_LOW + 10);
      hookHandler.attachHooker(GeneralInfoCategoryProviderHook.class, usageStatistics,
            HookHandlerService.PRIORITY_LOW + 90);
   }
}

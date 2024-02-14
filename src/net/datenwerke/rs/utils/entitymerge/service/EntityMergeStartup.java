package net.datenwerke.rs.utils.entitymerge.service;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.utils.entitymerge.service.terminal.commands.EntityMergeCommand;


public class EntityMergeStartup {
   
   @Inject
   public EntityMergeStartup(
         final HookHandlerService hookHandler,
         final Provider<EntityMergeCommand> entityMergeCommand
         ){
      /* Terminal */
      hookHandler.attachHooker(TerminalCommandHook.class, entityMergeCommand);
   }
}

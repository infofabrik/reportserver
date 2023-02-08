package net.datenwerke.rs.core.service.parameters;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.parameters.terminal.commands.CopyParameterDefinitionsSubcommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.CopySubCommandHook;

public class ParameterStartup {
   @Inject
   public ParameterStartup(HookHandlerService hookHandler,

         Provider<CopyParameterDefinitionsSubcommand> copyParameterDefinitionsSubcommand

      ) {
      hookHandler.attachHooker(CopySubCommandHook.class, copyParameterDefinitionsSubcommand);
   }
}

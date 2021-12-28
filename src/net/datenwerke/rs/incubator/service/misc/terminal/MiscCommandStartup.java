package net.datenwerke.rs.incubator.service.misc.terminal;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.service.eximport.terminal.hooks.ImportSubCommandHook;
import net.datenwerke.rs.incubator.service.misc.terminal.commands.ImportAllCommand;
import net.datenwerke.rs.incubator.service.misc.terminal.commands.SqlTerminalCommand;
import net.datenwerke.rs.incubator.service.misc.terminal.internaldb.ClearInternalDbCommand;
import net.datenwerke.rs.incubator.service.misc.terminal.virtualjrxmlprovider.MasterJrxmlContentProvider;
import net.datenwerke.rs.incubator.service.misc.terminal.virtualjrxmlprovider.SubreportJrxmlContentProvider;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualContentProviderHook;

public class MiscCommandStartup {

   @Inject
   public MiscCommandStartup(HookHandlerService hookHandler, Provider<SqlTerminalCommand> sqlCommand,

         Provider<ImportAllCommand> importAllCommand,

         Provider<MasterJrxmlContentProvider> masterJrxmlProvider,
         Provider<SubreportJrxmlContentProvider> subreportsJrxmlProvider,

         Provider<ClearInternalDbCommand> clearInternalDbCommand

//		Provider<TerminalExportContentProvider> exportContentProvider
   ) {

      hookHandler.attachHooker(TerminalCommandHook.class, sqlCommand);

      hookHandler.attachHooker(ImportSubCommandHook.class, importAllCommand);

      hookHandler.attachHooker(TerminalCommandHook.class, clearInternalDbCommand);

      hookHandler.attachHooker(VirtualContentProviderHook.class, masterJrxmlProvider);
      hookHandler.attachHooker(VirtualContentProviderHook.class, subreportsJrxmlProvider);

//		hookHandler.attachHooker(VirtualContentProviderHook.class, exportContentProvider);
   }
}

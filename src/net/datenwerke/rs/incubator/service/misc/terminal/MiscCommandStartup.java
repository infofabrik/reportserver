package net.datenwerke.rs.incubator.service.misc.terminal;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.service.eximport.terminal.hooks.ImportSubCommandHook;
import net.datenwerke.rs.incubator.service.misc.terminal.commands.ImportAllCommand;
import net.datenwerke.rs.incubator.service.misc.terminal.virtualjrxmlprovider.MasterJrxmlContentProvider;
import net.datenwerke.rs.incubator.service.misc.terminal.virtualjrxmlprovider.SubreportJrxmlContentProvider;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualContentProviderHook;

public class MiscCommandStartup {

   @Inject
   public MiscCommandStartup(HookHandlerService hookHandler,

         Provider<ImportAllCommand> importAllCommand,

         Provider<MasterJrxmlContentProvider> masterJrxmlProvider,
         Provider<SubreportJrxmlContentProvider> subreportsJrxmlProvider

         

//		Provider<TerminalExportContentProvider> exportContentProvider
   ) {

      hookHandler.attachHooker(ImportSubCommandHook.class, importAllCommand);

      hookHandler.attachHooker(VirtualContentProviderHook.class, masterJrxmlProvider);
      hookHandler.attachHooker(VirtualContentProviderHook.class, subreportsJrxmlProvider);

//		hookHandler.attachHooker(VirtualContentProviderHook.class, exportContentProvider);
   }
}

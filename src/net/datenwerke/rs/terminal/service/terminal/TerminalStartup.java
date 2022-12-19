package net.datenwerke.rs.terminal.service.terminal;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.hooks.ReloadConfigNotificationHook;
import net.datenwerke.rs.terminal.service.terminal.basecommands.AliasCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.CatCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.DescCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.EchoCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.ElizaCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.EnvCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.HelloWorldCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.HqlTerminalCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.MeminfoCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.SsltestCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.infocommand.InfoCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.infocommand.InfoDatasourceSubcommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.infocommand.InfoSubcommandHook;
import net.datenwerke.rs.terminal.service.terminal.hijacker.data.SplitTableResultHijacker;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalSessionHijackHook;
import net.datenwerke.rs.terminal.service.terminal.operator.InBackgroundOperator;
import net.datenwerke.rs.terminal.service.terminal.operator.PipeOperator;
import net.datenwerke.rs.terminal.service.terminal.operator.TerminalCommandOperator;

public class TerminalStartup {

   @Inject
   public TerminalStartup(HookHandlerService hookHandler,

         final Provider<DescCommand> descCommandProvider, 
         final Provider<ElizaCommand> elizaCommandProvider,
         final Provider<HelloWorldCommand> helloWorldCommandProvider, 
         final Provider<EnvCommand> envCommandProvider,
         final Provider<HqlTerminalCommand> hqlCommandProvider, 
         final Provider<MeminfoCommand> meminfoCommandProvider,
         final Provider<EchoCommand> echoCommandProvider, 
         final Provider<CatCommand> catCommandProvider,
         final Provider<SsltestCommand> ssltestCommandProvider,
         final Provider<InfoCommand> infoCommandProvider,
         final Provider<InfoDatasourceSubcommand> infoDatasourceSubcommandProvider,
         final Provider<AliasCommand> aliasCommandProvider,

         final Provider<InBackgroundOperator> inBgOperator, 
         final Provider<PipeOperator> pipeOperator,

         final Provider<SplitTableResultHijacker> splitTableResultHijacker
         ) {

      hookHandler.attachHooker(TerminalCommandHook.class, descCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, elizaCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, helloWorldCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, envCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, hqlCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, meminfoCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, echoCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, catCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, ssltestCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, infoCommandProvider);
      hookHandler.attachHooker(InfoSubcommandHook.class, infoDatasourceSubcommandProvider);
      
      /* alias command */
      hookHandler.attachHooker(TerminalCommandHook.class, aliasCommandProvider.get(), HookHandlerService.PRIORITY_LOWER);
      hookHandler.attachHooker(ReloadConfigNotificationHook.class, aliasCommandProvider.get(), HookHandlerService.PRIORITY_LOWER);

      hookHandler.attachHooker(LateInitHook.class, () -> aliasCommandProvider.get().reloadConfig(),
            HookHandlerService.PRIORITY_LOWER);

      hookHandler.attachHooker(TerminalCommandOperator.class, inBgOperator);
      hookHandler.attachHooker(TerminalCommandOperator.class, pipeOperator);

      hookHandler.attachHooker(TerminalSessionHijackHook.class, splitTableResultHijacker);
      
      
   }
}

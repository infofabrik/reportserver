package net.datenwerke.rs.terminal.service.terminal;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.basecommands.CatCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.DescCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.EchoCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.ElizaCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.HelloWorldCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.HqlTerminalCommand;
import net.datenwerke.rs.terminal.service.terminal.basecommands.MeminfoCommand;
import net.datenwerke.rs.terminal.service.terminal.hijacker.data.SplitTableResultHijacker;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalSessionHijackHook;
import net.datenwerke.rs.terminal.service.terminal.operator.InBackgroundOperator;
import net.datenwerke.rs.terminal.service.terminal.operator.PipeOperator;
import net.datenwerke.rs.terminal.service.terminal.operator.TerminalCommandOperator;

public class TerminalStartup {

   @Inject
   public TerminalStartup(
         HookHandlerService hookHandler,

         Provider<DescCommand> descCommandProvider,
         Provider<ElizaCommand> elizaCommandProvider,
         Provider<HelloWorldCommand> helloWorldCommandProvider,
         Provider<HqlTerminalCommand> hqlCommandProvider,
         Provider<MeminfoCommand> meminfoCommandProvider,
         Provider<EchoCommand> echoCommandProvider,
         Provider<CatCommand> catCommandProvider,

         Provider<InBackgroundOperator> inBgOperator,
         Provider<PipeOperator> pipeOperator,

         Provider<SplitTableResultHijacker> splitTableResultHijacker) {

      hookHandler.attachHooker(TerminalCommandHook.class, descCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, elizaCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, helloWorldCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, hqlCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, meminfoCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, echoCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, catCommandProvider);

      hookHandler.attachHooker(TerminalCommandOperator.class, inBgOperator);
      hookHandler.attachHooker(TerminalCommandOperator.class, pipeOperator);

      hookHandler.attachHooker(TerminalSessionHijackHook.class, splitTableResultHijacker);
   }
}

package net.datenwerke.rs.incubator.service.xslt;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.incubator.service.xslt.terminal.commands.XsltCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

public class XsltCommandStartup {

   @Inject
   public XsltCommandStartup(HookHandlerService hookHandler, Provider<XsltCommand> xsltProvider) {

      hookHandler.attachHooker(TerminalCommandHook.class, xsltProvider);
   }
}

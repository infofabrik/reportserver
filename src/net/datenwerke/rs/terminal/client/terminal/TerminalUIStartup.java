package net.datenwerke.rs.terminal.client.terminal;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.history.HistoryUiService;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.client.terminal.hookers.ClearScreenHooker;
import net.datenwerke.rs.terminal.client.terminal.hookers.JsCommandProcessor;
import net.datenwerke.rs.terminal.client.terminal.hookers.MessageCommandProcessor;
import net.datenwerke.rs.terminal.client.terminal.hookers.OverlayCommandProcessor;
import net.datenwerke.rs.terminal.client.terminal.hooks.ClientCommandHook;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;
import net.datenwerke.rs.terminal.client.terminal.security.TerminalGenericTargetIdentifier;
import net.datenwerke.rs.terminal.client.terminal.security.TerminalSecurityTargetDomainHooker;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

public class TerminalUIStartup {

   @Inject
   public TerminalUIStartup(
         final WaitOnEventUIService waitOnEventService,
         final TerminalUIService terminalService, 
         final SecurityUIService securityService,
         final HookHandlerService hookHandler, 
         final Provider<ClearScreenHooker> clearScreenCmd,
         final TerminalSecurityTargetDomainHooker securityTargetDomain,
         final HistoryUiService historyService, 
         final Provider<MessageCommandProcessor> messageCommandProcessor,
         final Provider<OverlayCommandProcessor> overlayCommandProcessor, 
         final Provider<JsCommandProcessor> jsCommandProcessor
         ) {

      hookHandler.attachHooker(CommandResultProcessorHook.class, messageCommandProcessor);
      hookHandler.attachHooker(CommandResultProcessorHook.class, overlayCommandProcessor);
      hookHandler.attachHooker(CommandResultProcessorHook.class, jsCommandProcessor);

      /* security */
      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

      /* attach terminal */
      waitOnEventService.callbackOnEvent(SecurityUIService.REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED,
            ticket -> {
               /* init terminal */
               if (securityService.hasRight(TerminalGenericTargetIdentifier.class, ExecuteDto.class)) {
                  hookHandler.attachHooker(ClientCommandHook.class, clearScreenCmd);

                  terminalService.initTerminal();
               }

               waitOnEventService.signalProcessingDone(ticket);
            });
      
      /* configureHistory */
      historyService.addHistoryCallback(TerminalUIModule.TERMINAL_ID, location -> terminalService.displayTerminalMaximizedWindow());
      historyService.addHistoryCallback(TerminalUIModule.TERMINAL_ID, location -> terminalService.displayTerminalMaximizedWindowWithCommand(location));
   }
}

package net.datenwerke.rs.scripting.client.scripting;

import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.codemirror.hooks.CodeMirrorKeyboardHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.enterprise.client.EnterpriseCheckUiModule;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.scripting.client.scripting.codemirror.plugin.GroovyImportCompleter;
import net.datenwerke.rs.scripting.client.scripting.hookers.ScriptingCommandResultProcessorHooker;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIService;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;

public class ScriptingUiStartup {

   @Inject
   public ScriptingUiStartup(
         HookHandlerService hookHandler, 
         final ScriptingDao scriptingDao,
         final TerminalUIService terminalService, 
         final WaitOnEventUIService waitOnEventService,

         Provider<ScriptingCommandResultProcessorHooker> commandResultProcessor,

         Provider<GroovyImportCompleter> groovyImportCompleterProvider,

         final EnterpriseUiService enterpriseUiService
         ) {

      hookHandler.attachHooker(CodeMirrorKeyboardHook.class, groovyImportCompleterProvider);

      hookHandler.attachHooker(CommandResultProcessorHook.class, commandResultProcessor);

      /* execute loginScript */
      waitOnEventService.callbackOnEvent(EnterpriseCheckUiModule.REPORTSERVER_ENTERPRISE_DETERMINED_AFTER_LOGIN,
            new SynchronousCallbackOnEventTrigger() {
               public void execute(final WaitOnEventTicket ticket) {
                  if (!enterpriseUiService.isEnterprise())
                     waitOnEventService.signalProcessingDone(ticket);
                  else {
                     scriptingDao.executeLoginScript(new RsAsyncCallback<List<CommandResultDto>>() {
                        public void onSuccess(final List<CommandResultDto> result) {
                           Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                              @Override
                              public void execute() {
                                 if (null != result)
                                    for (CommandResultDto res : result)
                                       terminalService.processExternalResult(res);

                              }
                           });
                           waitOnEventService.signalProcessingDone(ticket);
                        };

                        @Override
                        public void onFailure(Throwable caught) {
                           waitOnEventService.signalProcessingDone(ticket);
                        }
                     });
                  }
               }
            });

   }

}

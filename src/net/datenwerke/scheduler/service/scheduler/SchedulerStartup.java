package net.datenwerke.scheduler.service.scheduler;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.scheduler.service.scheduler.nlp.hooks.NlpTriggerParserHook;
import net.datenwerke.scheduler.service.scheduler.nlp.parsers.BaseNlpParser;

public class SchedulerStartup {

   @Inject
   public SchedulerStartup(HookHandlerService hookHandler,

         Provider<BaseNlpParser> nlpParser) {

      hookHandler.attachHooker(NlpTriggerParserHook.class, nlpParser, HookHandlerService.PRIORITY_LOWER);
   }
}

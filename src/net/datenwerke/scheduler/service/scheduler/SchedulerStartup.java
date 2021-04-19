package net.datenwerke.scheduler.service.scheduler;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.scheduler.service.scheduler.nlp.hooks.NlpTriggerParserHook;
import net.datenwerke.scheduler.service.scheduler.nlp.parsers.BaseNlpParser;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class SchedulerStartup {

	@Inject
	public SchedulerStartup(
		HookHandlerService hookHandler,
		
		Provider<BaseNlpParser> nlpParser
		) {
		
		hookHandler.attachHooker(NlpTriggerParserHook.class, nlpParser, HookHandlerService.PRIORITY_LOWER);
	}
}

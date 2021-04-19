package net.datenwerke.scheduler.service.scheduler.nlp.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;

public interface NlpTriggerParserHook extends Hook {

	AbstractTrigger parseExpression(String expression);
}

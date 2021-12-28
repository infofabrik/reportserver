package net.datenwerke.scheduler.service.scheduler.nlp;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.nlp.hooks.NlpTriggerParserHook;

public class NlpTriggerServiceImpl implements NlpTriggerService {

   @Inject
   private HookHandlerService hookHandlerService;

   @Override
   public AbstractTrigger parseExpression(String expression) {
      for (NlpTriggerParserHook parser : hookHandlerService.getHookers(NlpTriggerParserHook.class)) {
         AbstractTrigger trigger = parser.parseExpression(expression);
         if (null != trigger)
            return trigger;
      }
      return null;
   }

}

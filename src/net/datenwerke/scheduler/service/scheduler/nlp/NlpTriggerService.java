package net.datenwerke.scheduler.service.scheduler.nlp;

import com.google.inject.ImplementedBy;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;

@ImplementedBy(NlpTriggerServiceImpl.class)
public interface NlpTriggerService {

   AbstractTrigger parseExpression(String expression);
}

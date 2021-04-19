package net.datenwerke.scheduler.service.scheduler.nlp;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;

import com.google.inject.ImplementedBy;

@ImplementedBy(NlpTriggerServiceImpl.class)
public interface NlpTriggerService {

	AbstractTrigger parseExpression(String expression);
}

package net.datenwerke.gxtdto.client.eventbus;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class EventBusUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

      requestStaticInjection(EventBusHelper.class);
   }

}

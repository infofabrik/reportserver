package net.datenwerke.gxtdto.client.servercommunication.callback;

import com.google.gwt.inject.client.AbstractGinModule;

public class CallbackHandlerUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      requestStaticInjection(HandledAsyncCallback.class);

   }

}

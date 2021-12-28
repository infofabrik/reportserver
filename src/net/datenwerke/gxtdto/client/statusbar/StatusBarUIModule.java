package net.datenwerke.gxtdto.client.statusbar;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * 
 *
 */
public class StatusBarUIModule extends AbstractGinModule {

   @Override
   protected void configure() {

      /* configure service */
      bind(StatusBarUIService.class).to(StatusBarUIServiceImpl.class).in(Singleton.class);
   }

}

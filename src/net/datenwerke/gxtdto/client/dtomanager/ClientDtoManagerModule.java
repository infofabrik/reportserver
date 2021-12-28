package net.datenwerke.gxtdto.client.dtomanager;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.client.dtomanager.stores.DtoAwareListStore;
import net.datenwerke.gxtdto.client.dtomanager.stores.DtoAwareTreeStore;

/**
 * 
 *
 */
public class ClientDtoManagerModule extends AbstractGinModule {

   @Override
   protected void configure() {
      /* bind utils */
      bind(ProxyIdGenerator.class).to(ProxyIdGeneratorImpl.class).in(Singleton.class);

      /* bind service */
      bind(ClientDtoManagerService.class).to(ClientDtoManagerServiceImpl.class).in(Singleton.class);

      /* static injection */
      requestStaticInjection(Dao.class,

            DtoAwareTreeStore.class, DtoAwareListStore.class

      );
   }

}

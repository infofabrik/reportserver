package net.datenwerke.rs.birt.service.datasources;

import com.google.inject.PrivateModule;

public class BirtDatasourceModule extends PrivateModule {

   @Override
   protected void configure() {
      bind(BirtDatasourceService.class).to(BirtDatasourceServiceImpl.class);
      bind(BirtDatasourceStartup.class).asEagerSingleton();
   }

}

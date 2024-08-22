package net.datenwerke.rs.adminutils.service.datasourcetester;

import com.google.inject.AbstractModule;

public class DatasourceTesterModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(DatasourceTesterService.class).to(DatasourceTesterServiceImpl.class);
   }

}

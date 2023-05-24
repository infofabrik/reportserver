package net.datenwerke.rs.pkg.service.pkg;

import com.google.inject.AbstractModule;

public class PkgModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(PackagedScriptHelperService.class).to(PackagedScriptHelperServiceImpl.class);
      
      bind(PkgStartup.class).asEagerSingleton();
   }

}

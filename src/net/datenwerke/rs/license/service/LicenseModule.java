package net.datenwerke.rs.license.service;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import net.datenwerke.rs.license.service.genrights.GenRightsLicenseModule;

public class LicenseModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(LicenseService.class).to(LicenseServiceImpl.class).in(Singleton.class);

      bind(LicenseStartup.class).asEagerSingleton();

      /* rights */
      install(new GenRightsLicenseModule());
   }

}

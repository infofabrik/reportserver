package net.datenwerke.rs.adminutils.service;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoModule;
import net.datenwerke.rs.adminutils.service.systemconsole.genrights.GenRightsSystemConsoleModule;

public class SystemConsoleModule extends AbstractModule {

   @Override
   protected void configure() {
      install(new GeneralInfoModule());

      /* rights */
      install(new GenRightsSystemConsoleModule());
   }

}

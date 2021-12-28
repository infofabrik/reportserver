package net.datenwerke.rs.incubator.service.outputformatauth;

import com.google.inject.AbstractModule;

/**
 * Allows to specifiy restrictions on export formats for reports.
 *
 */
public class OutputFormatAuthModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(OutputFormatAuthStartup.class).asEagerSingleton();
   }

}

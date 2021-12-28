package net.datenwerke.rs.incubator.service.xslt;

import com.google.inject.AbstractModule;

public class XsltCommandModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(XsltCommandStartup.class).asEagerSingleton();
   }

}

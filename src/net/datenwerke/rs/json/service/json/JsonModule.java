package net.datenwerke.rs.json.service.json;

import com.google.inject.AbstractModule;

public class JsonModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(JsonService.class).to(JsonServiceImpl.class);
   }

}

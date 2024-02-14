package net.datenwerke.rs.json.service.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class JsonModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(JsonService.class).to(JsonServiceImpl.class);
   }

   @Provides
   public Gson provideGson() {
      return new Gson();
   }
   
   @Provides @DisableHtml
   public Gson provideGsonDisableHtml() {
      return new GsonBuilder().disableHtmlEscaping().create();
   }
}

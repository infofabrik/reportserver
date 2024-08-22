package net.datenwerke.rs.saiku.service.hooker;

import java.util.Map;

import javax.inject.Provider;

import com.google.inject.Inject;

import net.datenwerke.rs.saiku.service.hooks.SaikuQueryParameterAdapterHook;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.utils.juel.SimpleJuel;

public class SaikuJuelParameterAdapter implements SaikuQueryParameterAdapterHook {

   private final Provider<SimpleJuel> juelProvider;

   @Inject
   public SaikuJuelParameterAdapter(Provider<SimpleJuel> juelProvider) {
      this.juelProvider = juelProvider;
   }

   @Override
   public void adaptParameters(Map<String, String> parameters, SaikuReport report) {
      for (String key : parameters.keySet()) {
         String value = parameters.get(key);

         SimpleJuel juel = juelProvider.get();
         juel.addReplacement("key", key);
         juel.addReplacement("value", value);

         String adapted = juel.parse(value);

         parameters.put(key, adapted);
      }
   }

}

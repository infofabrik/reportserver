package net.datenwerke.rs.core.service.guice;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.AbstractModule;

public abstract class AbstractReportServerModule extends AbstractModule {

   protected static Set<Class<?>> staticInjectionPerformed = new HashSet<Class<?>>();

   @Override
   protected void requestStaticInjection(java.lang.Class<?>... types) {
      if (staticInjectionPerformed.contains(getClass()))
         return;
      staticInjectionPerformed.add(getClass());
      super.requestStaticInjection(types);
   };
}

package net.datenwerke.rs;

import java.util.Properties;

import com.google.inject.persist.jpa.JpaPersistModule;

import net.datenwerke.gf.service.jpa.annotations.JpaUnit;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class ReportServerPUModule extends AbstractReportServerModule {

   public final static String JPA_UNIT_NAME = "reportServerPU";
   private final static Properties jpaProperties = new Properties();

   @Override
   protected void configure() {
      /* database */
      bindConstant().annotatedWith(JpaUnit.class).to(JPA_UNIT_NAME); // $NON-NLS-1$
      bind(Properties.class).annotatedWith(JpaUnit.class).toInstance(jpaProperties);
      bind(ReportServerPUStartup.class).asEagerSingleton();

      install(new JpaPersistModule(JPA_UNIT_NAME).properties(jpaProperties));
   }
}

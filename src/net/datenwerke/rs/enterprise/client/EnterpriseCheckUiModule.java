package net.datenwerke.rs.enterprise.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class EnterpriseCheckUiModule extends AbstractGinModule {

   /**
    * 
    */
   public static final String REPORTSERVER_ENTERPRISE_DETERMINED_BEFORE_LOGIN = "REPORTSERVER_ENTERPRISE_DETERMINED";
   public static final String REPORTSERVER_ENTERPRISE_DETERMINED_AFTER_LOGIN = "REPORTSERVER_ENTERPRISE_DETERMINED_AFTER_LOGIN";

   public static final String COMMUNITY_LICENSE = "Community Edition";
   public static final String ENTERPRISE_LICENSE = "Enterprise Edition";
   
   @Override
   protected void configure() {
      bind(EnterpriseUiService.class).to(EnterpriseUiServiceImpl.class).in(Singleton.class);
      bind(EnterpriseCheckUiStartup.class).asEagerSingleton();
   }

}

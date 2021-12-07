package net.datenwerke.rs.ldap.service.ldap;

import com.google.inject.AbstractModule;

public class LdapModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(LdapService.class).to(LdapServiceImpl.class);
      
      /* startup */
      bind(LdapStartup.class).asEagerSingleton();
   }

}
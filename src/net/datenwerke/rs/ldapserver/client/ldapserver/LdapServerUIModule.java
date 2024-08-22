package net.datenwerke.rs.ldapserver.client.ldapserver;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.ldapserver.client.ldapserver.dto.LdapServerDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class LdapServerUIModule extends AbstractGinModule {

   public final static BaseIcon ICON = BaseIcon.UPLOAD;
   public final static Class<? extends LdapServerDto> TYPE = LdapServerDto.class;

   @Override
   protected void configure() {
      bind(LdapServerUIStartup.class).asEagerSingleton();
   }

}

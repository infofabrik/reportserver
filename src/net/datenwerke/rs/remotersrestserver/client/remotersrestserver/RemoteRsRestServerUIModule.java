package net.datenwerke.rs.remotersrestserver.client.remotersrestserver;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.RemoteRsRestServerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class RemoteRsRestServerUIModule extends AbstractGinModule {

   public final static BaseIcon ICON = BaseIcon.LAPTOP;
   public final static Class<? extends RemoteServerDefinitionDto> TYPE = RemoteRsRestServerDto.class;

   @Override
   protected void configure() {
      bind(RemoteRsRestServerUIStartup.class).asEagerSingleton();
   }

}

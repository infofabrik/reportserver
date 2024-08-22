package net.datenwerke.rs.remoteserver.client.remoteservermanager.config;

import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerContainerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;

/**
 * 
 *
 */
public interface RemoteServerDefinitionConfigConfigurator {

   void inheritChanges(RemoteServerDefinitionDto RemoteServerDefinitionDto);

   boolean consumes(RemoteServerDefinitionDto RemoteServerDefinitionDto);

   boolean isValid(RemoteServerContainerDto RemoteServerContainer);

   boolean isReloadOnRemoteServerChange();
}

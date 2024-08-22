package net.datenwerke.rs.remoteserver.client.remoteservermanager;

import net.datenwerke.rs.remoteserver.client.remoteservermanager.config.RemoteServerDefinitionConfigConfigurator;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;

/**
 * 
 *
 */
public interface RemoteServerUIService {

   public RemoteServerDefinitionConfigConfigurator getConfigurator(Class<? extends RemoteServerDefinitionDto> configClazz);

}

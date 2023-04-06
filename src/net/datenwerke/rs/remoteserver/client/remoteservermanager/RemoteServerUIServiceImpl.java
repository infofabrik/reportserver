package net.datenwerke.rs.remoteserver.client.remoteservermanager;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.remoteserver.client.remoteservermanager.config.RemoteServerDefinitionConfigConfigurator;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;

/**
 * 
 *
 */
public class RemoteServerUIServiceImpl implements RemoteServerUIService {

   private Map<Class<? extends RemoteServerDefinitionDto>, Provider<? extends RemoteServerDefinitionConfigConfigurator>> configuratorLookup;


   @Inject
   public RemoteServerUIServiceImpl(
         ) {

   }

   public RemoteServerDefinitionConfigConfigurator getConfigurator(Class<? extends RemoteServerDefinitionDto> configClazz) {
      if (null == configuratorLookup)
         initConfigurator();

      Provider<? extends RemoteServerDefinitionConfigConfigurator> provider = configuratorLookup.get(configClazz);
      if (null == provider)
         throw new IllegalStateException("I should probably know the provider for " + configClazz.getName()); //$NON-NLS-1$
      return provider.get();
   }

   protected void initConfigurator() {
      configuratorLookup = new HashMap<Class<? extends RemoteServerDefinitionDto>, Provider<? extends RemoteServerDefinitionConfigConfigurator>>();

   }

}

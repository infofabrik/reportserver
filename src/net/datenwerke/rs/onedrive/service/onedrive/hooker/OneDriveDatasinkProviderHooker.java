package net.datenwerke.rs.onedrive.service.onedrive.hooker;

import java.util.Arrays;
import java.util.Collection;

import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;

public class OneDriveDatasinkProviderHooker implements DatasinkProviderHook {

   @Override
   public Collection<? extends Class<? extends DatasinkDefinition>> getDatasinks() {
      return (Collection<? extends Class<? extends DatasinkDefinition>>) Arrays
            .asList(new Class[] { OneDriveDatasink.class });
   }

}
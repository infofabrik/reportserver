package net.datenwerke.rs.localfsdatasink.service.localfsdatasink.hooker;

import java.util.Arrays;
import java.util.Collection;

import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;

public class LocalFileSystemDatasinkProviderHooker implements DatasinkProviderHook {

   @Override
   public Collection<? extends Class<? extends DatasinkDefinition>> getDatasinks() {
      return (Collection<? extends Class<? extends DatasinkDefinition>>) Arrays
            .asList(new Class[] { LocalFileSystemDatasink.class });
   }

}
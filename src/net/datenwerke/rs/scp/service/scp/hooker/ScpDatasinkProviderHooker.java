package net.datenwerke.rs.scp.service.scp.hooker;

import java.util.Arrays;
import java.util.Collection;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;

public class ScpDatasinkProviderHooker implements DatasinkProviderHook {

   @Override
   public Collection<? extends Class<? extends DatasinkDefinition>> getDatasinks() {
      return (Collection<? extends Class<? extends DatasinkDefinition>>) Arrays
            .asList(new Class[] { ScpDatasink.class });
   }

}

package net.datenwerke.rs.printer.service.printer.hooker;

import java.util.Arrays;
import java.util.Collection;

import net.datenwerke.rs.printer.service.printer.definitions.PrinterDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;

public class PrinterDatasinkProviderHooker implements DatasinkProviderHook {

   @Override
   public Collection<? extends Class<? extends DatasinkDefinition>> getDatasinks() {
      return (Collection<? extends Class<? extends DatasinkDefinition>>) Arrays
            .asList(new Class[] { PrinterDatasink.class });
   }

}

package net.datenwerke.rs.printer.service.printer;

import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_ID;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_KEY;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DATASINK_NAME;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_DISABLED;
import static net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule.PROPERTY_DEFAULT_SCHEDULING_ENABLED;

import java.util.Optional;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.printer.service.printer.annotations.DefaultPrinterDatasink;
import net.datenwerke.rs.printer.service.printer.definitions.PrinterDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;

public class PrinterModule extends AbstractModule {

   private static final String PROPERTY_DATASINK = "printer";
   public static final String PROPERTY_DEFAULT_PRINTER_DATASINK_ID = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_ID;
   public static final String PROPERTY_DEFAULT_PRINTER_DATASINK_NAME = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_NAME;
   public static final String PROPERTY_DEFAULT_PRINTER_DATASINK_KEY = PROPERTY_DATASINK + "."
         + PROPERTY_DEFAULT_DATASINK_KEY;
   public static final String PROPERTY_PRINTER_DISABLED = PROPERTY_DATASINK + PROPERTY_DEFAULT_DISABLED;
   public static final String PROPERTY_PRINTER_SCHEDULING_ENABLED = PROPERTY_DATASINK
         + PROPERTY_DEFAULT_SCHEDULING_ENABLED;

   @Override
   protected void configure() {
      requestStaticInjection(PrinterDatasink.class);

      bind(PrinterStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DefaultPrinterDatasink
   public Optional<PrinterDatasink> provideDefaultDatasink(DatasinkTreeService datasinkService) {
      return datasinkService.getDefaultDatasink(PrinterDatasink.class, PROPERTY_DEFAULT_PRINTER_DATASINK_ID,
            PROPERTY_DEFAULT_PRINTER_DATASINK_NAME, PROPERTY_DEFAULT_PRINTER_DATASINK_KEY);
   }

}
package net.datenwerke.eximport;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryProvider;

import net.datenwerke.eximport.ex.ExportItemConfig;
import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.eximport.ex.ExportSupervisorFactory;
import net.datenwerke.eximport.ex.objectexporters.BasicObjectExporter;
import net.datenwerke.eximport.ex.objectexporters.BasicObjectExporterFactory;
import net.datenwerke.eximport.ex.objectexporters.EntityObjectExporter;
import net.datenwerke.eximport.ex.objectexporters.EntityObjectExporterFactory;
import net.datenwerke.eximport.im.ImportSupervisor;
import net.datenwerke.eximport.im.ImportSupervisorFactory;
import net.datenwerke.eximport.im.objectimporters.BasicObjectImporter;
import net.datenwerke.eximport.im.objectimporters.BasicObjectImporterFactory;

/**
 * 
 *
 */
public class ExImportModule extends AbstractModule {

   @Override
   protected void configure() {
      /* services */
      bind(ImportService.class).to(ImportServiceImpl.class).in(Singleton.class);
      bind(ExportService.class).to(ExportServiceImpl.class).in(Singleton.class);
      
      bind(ExportDataAnalyzerService.class).to(ExportDataAnalyzerServiceImpl.class);

      /* assisted injection factories */
      bind(ExportSupervisorFactory.class)
            .toProvider(FactoryProvider.newFactory(ExportSupervisorFactory.class, ExportSupervisor.class));
      bind(BasicObjectExporterFactory.class)
            .toProvider(FactoryProvider.newFactory(BasicObjectExporterFactory.class, BasicObjectExporter.class));
      bind(EntityObjectExporterFactory.class)
            .toProvider(FactoryProvider.newFactory(EntityObjectExporterFactory.class, EntityObjectExporter.class));

      bind(ImportSupervisorFactory.class)
            .toProvider(FactoryProvider.newFactory(ImportSupervisorFactory.class, ImportSupervisor.class));
      bind(BasicObjectImporterFactory.class)
            .toProvider(FactoryProvider.newFactory(BasicObjectImporterFactory.class, BasicObjectImporter.class));

      /* static injection */
      requestStaticInjection(ExportItemConfig.class, EnclosedObjectConfig.class);

      /* startup */
      bind(ExImportStartup.class).asEagerSingleton();
   }

}

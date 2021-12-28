package net.datenwerke.rs.jxlsreport.service.jxlsreport;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.metadata.JxlsMetadataExporter;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.metadata.JxlsPlainMetadataExporter;

public class JxlsReportModule extends AbstractModule {

   @Override
   protected void configure() {
      /* bind metadata exporter */
      Multibinder<JxlsMetadataExporter> metadataExporterBinder = Multibinder.newSetBinder(binder(),
            JxlsMetadataExporter.class);
      metadataExporterBinder.addBinding().to(JxlsPlainMetadataExporter.class);

      bind(JxlsReportService.class).to(JxlsReportServiceImpl.class);
      bind(JxlsReportStartup.class).asEagerSingleton();

   }

}

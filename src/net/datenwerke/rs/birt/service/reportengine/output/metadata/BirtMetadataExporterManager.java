package net.datenwerke.rs.birt.service.reportengine.output.metadata;

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.reportmanager.metadata.AbstractReportMetadataExporterManager;

public class BirtMetadataExporterManager extends AbstractReportMetadataExporterManager<BirtMetadataExporter> {

   @Inject
   public BirtMetadataExporterManager(Provider<Set<BirtMetadataExporter>> exporters) {
      super(exporters);
   }

}

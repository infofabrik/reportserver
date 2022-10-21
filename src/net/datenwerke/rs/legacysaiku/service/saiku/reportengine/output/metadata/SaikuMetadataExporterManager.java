package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.metadata;

import java.util.Set;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.reportmanager.metadata.AbstractReportMetadataExporterManager;

public class SaikuMetadataExporterManager extends AbstractReportMetadataExporterManager<SaikuMetadataExporter> {

   @Inject
   public SaikuMetadataExporterManager(Provider<Set<SaikuMetadataExporter>> exporters) {
      super(exporters);
   }

}

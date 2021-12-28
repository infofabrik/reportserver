package net.datenwerke.rs.base.service.reportengines.table.output.metadata;

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.reportmanager.metadata.AbstractReportMetadataExporterManager;

/**
 * Manages the output generators for table reports.
 * 
 * 
 * <p>
 * Currently only one instance of each output generator is created. This might
 * lead to problems and should be investigated.
 * </p>
 *
 *
 */
public class TableMetadataExporterManager extends AbstractReportMetadataExporterManager<TableMetadataExporter> {

   @Inject
   public TableMetadataExporterManager(Provider<Set<TableMetadataExporter>> exporters) {
      super(exporters);
   }

}

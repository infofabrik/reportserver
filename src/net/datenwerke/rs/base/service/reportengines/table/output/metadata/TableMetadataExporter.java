package net.datenwerke.rs.base.service.reportengines.table.output.metadata;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.core.service.reportmanager.metadata.ReportMetadataExporter;

/**
 * 
 *
 */
public interface TableMetadataExporter extends ReportMetadataExporter {

	public void visitColumn(Column column);
	
	public void beginColumnSection();
	
	public void beginFilterSection();
	
	public void visitFilter(Column column);
}

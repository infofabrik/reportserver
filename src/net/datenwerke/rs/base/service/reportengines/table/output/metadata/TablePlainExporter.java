package net.datenwerke.rs.base.service.reportengines.table.output.metadata;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.core.service.reportmanager.metadata.AbstractPlainMetadataExporter;

/**
 * 
 *
 */
public class TablePlainExporter extends AbstractPlainMetadataExporter implements TableMetadataExporter {

	@Override
	public void beginColumnSection() {
		beginSection("Selected Columns");
	}

	@Override
	public void visitColumn(Column column) {
		dataBuilder.append(column.getName() + " - " + column.getAlias() + " - " + column.getDescription() +  "\n");
	}

	@Override
	public void beginFilterSection() {
		beginSection("Filtered Columns");
	}

	@Override
	public void visitFilter(Column column) {
		dataBuilder.append(column.getName() + " - " + column.getAlias() + " - " + column.getDescription() + "\n");
	}

}
